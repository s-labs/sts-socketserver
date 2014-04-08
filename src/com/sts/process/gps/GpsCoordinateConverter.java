package com.sts.process.gps;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GpsCoordinateConverter {

	@Bean
	public GpsCoordinateConverter gpsCoordinateConverter(){
		
		return new GpsCoordinateConverter();
	}
	private Double coordinate;
	
	public Double getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Double coordinate) {
		this.coordinate = coordinate;
	}

	public Double convertCoordinate(){
		
		try{
				String s4 = null,s5 = null;
				char[] ch=coordinate.toString().toCharArray();
				for(int i=0;i<ch.length;i++){

					//System.out.println(ch[i]);
				}
				//System.out.println(ch.length+" --> len");
				char[] s1=Arrays.copyOfRange(ch, 0, 2);	
				char[] s2=Arrays.copyOfRange(ch, 2, 4);	
				char[] s3=Arrays.copyOfRange(ch, 4, 9);	
				//System.out.println(new String(s1)+"  s1..."+new String(s2)+"  s2..."+new String(s3)+"  s3...");

				 s4=new String(s2)+new String(s3);
				 s5=new String(s1);
				//System.out.println(Float.parseFloat(s5)+Float.parseFloat(s4)/60);
				return (Double.parseDouble(s5))+(Double.parseDouble(s4)/60);

		}
		catch(Exception e){


			return null;
		}
	}
	public String assignSigns(String firstPole,String secondPole){
		String firstSign=null,secondSign=null;
		if(firstPole.equals("N") && secondPole.equals("E") ){

			firstSign="+";
			secondSign="+";
		}
		if(firstPole.equals("S") && secondPole.equals("W") ){
			firstSign="-";
			secondSign="-";
		}
		if(firstPole.equals("N") && secondPole.equals("W") ){

			firstSign="+";
			secondSign="-";
		}
		if(firstPole.equals("S") && secondPole.equals("E") ){
			firstSign="-";
			secondSign="+";
		}
		return firstSign+","+secondSign;
	}
}
