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

import com.data.grps.GprsData;
import com.sts.dao.DailyGpsDao;
import com.sts.dao.DailyRfidData;
import com.sts.dao.RfidDao;
import com.sts.serviceimpl.DailyRfidDataImpl;
import com.sts.serviceimpl.GpsServiceImpl;
import com.sts.serviceimpl.RfidServiceImpl;
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
			
			//logger.info("Revieved data is [ " + line + " ]");
			DataSender dataSender = context.getBean("dataSender",DataSender.class);
			dataSender.setData(line);
			ProcessData processData = context.getBean("processData",ProcessData.class);
			boolean validData=processData.isDatavalid(dataSender.getData());
			if(validData==false){
				logger.info("invalid data recieved [ "+dataSender.getData()+" ]");
			}
			else{
				//logger.info("[ "+dataSender.getData()+" ]");
				String dataType=processData.findDataType(dataSender.getData());
				if(dataType==null){
					logger.info("invalid data recieved [ "+dataSender.getData()+" ] ==> ignoring.....");
				}
				else if(dataType.equals("RFID")){
					RfidServiceImpl rfidServiceImpl=context.getBean("rfidServiceImpl",RfidServiceImpl.class);
					String data=dataSender.getData().trim();
					String arr[] = data.split(",");
					RfidDao rfidDao=rfidServiceImpl.getRfidByNumber(arr[1]);
					if(rfidDao==null){
						//System.out.println(rfidDao);
						logger.info("RFID data recieved [ "+dataSender.getData()+" ] doesnot exists in db or doesnot assigned to no one");
					}
					else{
						
							//logger.info("RFID data recieved [ "+dataSender.getData()+" ] exists with type [ "+rfidDao.getType()+" ]");
							GprsData gprsData=context.getBean("gprsData",GprsData.class);
							Double lat=gprsData.processLat(arr[3]);
							if(lat==null){
								logger.info("GPS data recieved [ "+dataSender.getData()+" ] is not valid");
							}
							else{
								Double Long=gprsData.processLat(arr[5]);
								if(Long==null){
									logger.info("GPS data recieved [ "+dataSender.getData()+" ] is not valid");
								}
								else{
									
									String signs[]=gprsData.assignSigns(arr[4],arr[6]).split(",");
									Double Lattitude=Double.parseDouble(signs[0]+lat.toString());
									Double Longitude=Double.parseDouble(signs[1]+Long.toString());
									String time=arr[8];
									//logger.info("After Process : Lat = "+Lattitude+" Long= "+Longitude);
								
									DailyGpsDao gpsDao=context.getBean("gpsDao",DailyGpsDao.class);
									Date date = new Date();
									String current_date= new SimpleDateFormat("dd-MM-yyyy").format(date);
									gpsDao.setArrived_time(time);
									gpsDao.setLattitude(Lattitude);
									gpsDao.setLongitude(Longitude);
									gpsDao.setDate(current_date);
									try{
										//gpsServiceImpl.insertGpsData(gpsDao);
										DailyRfidData dailyRfidData=context.getBean(DailyRfidData.class);
										dailyRfidData.setArrived_time(time);
										dailyRfidData.setDate(current_date);
										dailyRfidData.setLattitude(Lattitude);
										dailyRfidData.setLongitude(Longitude);
										dailyRfidData.setRfid_number(rfidDao.getRfid_number());
										//logger.info("Daily Rfid Data: "+dailyRfidData);
										DailyRfidDataImpl dailyRfidDataImpl=context.getBean(DailyRfidDataImpl.class);
										dailyRfidDataImpl.insertDailyRfidData(dailyRfidData);
										logger.info("RFID data recieved [ "+dataSender.getData()+" ] exists with type [ "+rfidDao.getType()+" ]"+"RFID-- GPS data "+gpsDao);
									}
									catch(Exception e){
										logger.info("Unableto insert RFID data: due to [ "+e+" ]");
									}
									
								}
							}
					}
				}
				else if(dataType.equals("GPRS")){
					GprsData gprsData=context.getBean("gprsData",GprsData.class);
					boolean isGprsDataValid=gprsData.isGprsDataValid(dataSender.getData());
					if(isGprsDataValid==false){ //not Valid GPRS data received
						logger.info("GPRS data recieved [ "+dataSender.getData()+" ] is not valid");
					}
					else{ //Valid GPRS data received
						//logger.info("GPRS data received [ "+dataSender.getData()+" ]");
						String data=dataSender.getData().trim();
						String arr[] = data.split(",");
						Double lat=gprsData.processLat(arr[1]);
						if(lat==null){
							logger.info("GPRS data recieved [ "+dataSender.getData()+" ] is not valid");
						}
						else{
							Double Long=gprsData.processLat(arr[3]);
							if(Long==null){
								logger.info("GPRS data recieved [ "+dataSender.getData()+" ] is not valid");
							}
							else{
								
								String signs[]=gprsData.assignSigns(arr[2],arr[4]).split(",");
								Double Lattitude=Double.parseDouble(signs[0]+lat.toString());
								Double Longitude=Double.parseDouble(signs[1]+Long.toString());
								String time=arr[6];
								//logger.info("After Process : Lat = "+Lattitude+" Long= "+Longitude);
								GpsServiceImpl gpsServiceImpl=context.getBean("gpsServiceImpl",GpsServiceImpl.class);
								DailyGpsDao gpsDao=context.getBean("gpsDao",DailyGpsDao.class);
								Date date = new Date();
								String current_date= new SimpleDateFormat("dd-MM-yyyy").format(date);
								gpsDao.setArrived_time(time);
								gpsDao.setLattitude(Lattitude);
								gpsDao.setLongitude(Longitude);
								gpsDao.setDate(current_date);
								try{
									gpsServiceImpl.insertGpsData(gpsDao);
									logger.info("inserted GPS data "+gpsDao);
								}
								catch(Exception e){
									logger.info("Unableto insert GPS data: due to [ "+e+" ]");
								}
								
							}
						}
					}
				}
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