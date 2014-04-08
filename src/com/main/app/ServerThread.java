package com.main.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.sts.process.gps.ProcessGpsData;
import com.sts.process.gps.ReceivedGpsData;
import com.sts.process.rfid.ProcessRfidData;
import com.sts.process.rfid.ReceivedRfidData;
//Server Thread Program
class ServerThread extends Thread {
	private static final Logger logger = Logger.getLogger(ServerThread.class);

	String line = null;
	BufferedReader is = null;
	PrintWriter os = null;
	Socket s = null;
	private ApplicationContext context;

	public ServerThread(Socket s, ApplicationContext context) {
		this.s = s;
		this.context = context;

	}

	public void run() {
		try {
			is = new BufferedReader(new InputStreamReader(s.getInputStream()));
			os = new PrintWriter(s.getOutputStream());

		} catch (IOException e) {
			logger.info("IO error in server thread");
		}

		try {
			line = is.readLine();

			os.println(line);
			os.flush();
			Date date = new Date();
			String current_date= new SimpleDateFormat("dd-MM-yyyy").format(date);
			//logger.info("Received data is: "+line);
			ProcessData processData=context.getBean(ProcessData.class);
			boolean is_data_valid=processData.isDatavalid(line);
			if(is_data_valid==true){
				String data_type=processData.findDataType(line);
				if(data_type.equals("RFID")){
					
					/*
					 * RFID Data logic
					 */
					ProcessRfidData processRfidData=context.getBean(ProcessRfidData.class);
					try{
						String arr[] = line.split(",");
						ReceivedRfidData receivedRfidData=context.getBean(ReceivedRfidData.class);
						receivedRfidData.setRfid_number(arr[1]);
						char c = arr[4].charAt(0);
						char c1 = arr[6].charAt(0);
						receivedRfidData.setFirstSign(c);
						receivedRfidData.setSecondSign(c1);
						receivedRfidData.setLattitude(Double.parseDouble(arr[3]));
						receivedRfidData.setLongitude(Double.parseDouble(arr[5]));
						receivedRfidData.setTime(arr[8]);
						receivedRfidData.setBus_id(arr[10]);
						
						receivedRfidData.setDate(current_date);
						processRfidData.setRfid(receivedRfidData);
						processRfidData.setContext(context);
						processRfidData.processRfidDataReceived();
						
					}
					catch(Exception e){
						e.printStackTrace();
						logger.info("invalid data recieved [ "+line+" ] ==> ignoring");
					}
				}
				else if(data_type.equals("GPS")){
					
					
					/*
					 * Gps Data logic
					 */
					String arr[] = line.split(",");
					ReceivedGpsData receivedGpsData=context.getBean(ReceivedGpsData.class);
					receivedGpsData.setBus_id(arr[8]);
					receivedGpsData.setDate(current_date);
					char c = arr[2].charAt(0);
					char c1 = arr[4].charAt(0);
					receivedGpsData.setFirstSign(c);
					receivedGpsData.setSecondSign(c1);
					receivedGpsData.setLattitude(Double.parseDouble(arr[1]));
					receivedGpsData.setLongitude(Double.parseDouble(arr[3]));
					receivedGpsData.setTime(arr[6]);
					ProcessGpsData processGpsData=context.getBean(ProcessGpsData.class);
					processGpsData.setContext(context);
					processGpsData.setGps(receivedGpsData);
					processGpsData.processGpsDataReceived();
				}
				else{
					logger.info("invalid data recieved [ "+line+" ] ==> ignoring.....");
				}
			}
			else{
				logger.info("invalid data recieved [ "+line+" ] ==> ignoring.....");
			}
		} catch (IOException e) {

			line = this.getName();
			logger.info("IO Error/ Client [" + s.getRemoteSocketAddress()+ " ] " + line + " terminated abruptly");
		} catch (NullPointerException e) {
			line = this.getName();
			logger.info("Client " + line + " Closed");
		}

		finally {
			try {

				if (is != null) {
					is.close();

				}

				if (os != null) {
					os.close();

				}
				if (s != null) {
					s.close();

				}
				logger.info("Connection Closing..");
			} catch (IOException ie) {
				logger.info("Socket Close Error");
			}
		}
	}
}