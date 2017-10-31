package com.wip.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.omg.CORBA.TRANSACTION_UNAVAILABLE;



public class RestAssuredWithTokenGenerator
{

	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		String response = null;
		String token = generateTokenWrappers_Portabality();
		String url1="https://apmi-stage.cisco.com/custcare/fs/entitlement/ebf/data/softwareproducts/item/annuities/?itemName=";
		
		String itemName="1001815";
		//String itemName=stritemName;
		String ampersand_Userid="&userId=";
		String userId="latrjohn";
		//String userId=entitledTo;

		URL obj = new URL(url1+itemName+ampersand_Userid+userId);
		
		//https://apmi-stage.cisco.com/custcare/fs/entitlement/ebf/data/softwareproducts/item/annuities/?itemName=1001815&userId=latrjohn
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");  //Method type is provided here
			con.setRequestProperty("Authorization",token);
			con.setRequestProperty("pose_as","kbushnick");
			
			Long Response_code = (long) con.getResponseCode(); 
	          
	         // logMessage("Response code of the API", "shows API response code", Response_code.toString(), "passed");
	        //  System.out.println(con.getResponseCode());
	          
	          if(Response_code==200 || Response_code==201 || Response_code==202 || Response_code==203 || Response_code==204) 
	          {
	          
			InputStreamReader isr = new InputStreamReader(con.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String inputLine;
			while ((inputLine = in.readLine()) != null)
			{
				//System.out.println("response  :"+inputLine);
				response=inputLine;
				boolean rc=false;
				rc = jsonResponsePortabalityGet(response);

				
			}
	}
	}
	          static boolean  jsonResponsePortabalityGet(String jasonresponse)
	          {
	        	  
	          try 
	          {
	        	  boolean check = false;
	        		//,String ItemFamilyGrpId,String ContainerId,String Method,String inventoryItemId
	        		LinkedList<String> lst =new LinkedList<String>();
	        		
	        			System.out.println("Response body :"+jasonresponse);
	        			JSONObject jsonObject = (JSONObject) JSONValue.parseWithException(jasonresponse);
	        			//if(jsonObject.get("responseDesc").toString().equalsIgnoreCase("SUCCESS")){
	        			//logMessage("Fetch the response getting from webservices", "Response should be available", "Response Nodes is available", "Passed");
	        		//	System.out.println(jsonObject.get("status"));
	        			//System.out.println(jsonObject.get("data"));
	        			//System.out.println(jsonObject.get("totalEntries"));
	        			
	        			JSONArray caseMgmt = (JSONArray) jsonObject.get("data");

	        			for (int j = 0; j < caseMgmt.size(); j++) 
	        			{
	        				
	        			
	        			
	        			if (jsonObject.get("status").toString().equalsIgnoreCase("success"))
	        			{
	        				JSONObject jsonObject21 = (JSONObject) caseMgmt.get(j);
	        				
	        				//logMessage("Verifying the status in the  reponse", "The status of response should be success", "The response status is <b>"+jsonObject.get("status")+"</b> ", "Passed");
	        				//logMessage("Verifying the Code in the  reponse", "The Code in the  response should be 200", "The response status is <b>"+jsonObject21.get("code")+"</b> ", "Passed");
	        			
	        				String jstrannuityId = (String) jsonObject21.get("annuityId").toString();
	        				System.out.println("API annuityId is " +  jstrannuityId);
	        			}
	        			}
	          }
				
			
	          catch (Exception e) 
	          {
				return false;
				
			}
	          return true;
	          
	          }
	  public  static String generateTokenWrappers_Portabality() 
      {
		  
//      	logMessage("Token is being generated for API", "Token must be generated", "check in next step","passed");
        String tokenRequest="", accessToken = "";
         try{			        	   
          tokenRequest = PostMethodForAccessToken("https://cloudsso-test.cisco.com/as/token.oauth2?grant_type=client_credentials&client_id=7e8cd36a81ec40c1b419a62f3e48111f&client_secret=85bF940CCfC44405937786F4E0fA597e", "", "");
          //System.out.println("===TOKEN REQUEST===" + tokenRequest);

          String tokenReqSplit[] = tokenRequest.split(",");
          for(String tokenReqParts:tokenReqSplit){
            if(tokenReqParts.contains("access_token")){
              String tokenReqAttrSplit[] = tokenReqParts.split(":");
              for(String token:tokenReqAttrSplit){
                String tokenReqQuotesSplit = token.replace("\"", "");
                accessToken="Bearer "+tokenReqQuotesSplit;
              }
//              logMessage("Access Token is   :","Token value is",accessToken,"passed");
            System.out.println("==ACCESS TOKEN===" + accessToken);
            }
          }

        }catch(Exception e){
          e.printStackTrace();
        }

        return accessToken;
      }
	  
	   public static String PostMethodForAccessToken(String url,String name,String password) throws Exception {
	          StringBuffer response = new StringBuffer();
	          URL obj = new URL(url);
	          HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	          con.setRequestMethod("POST");
	          int responseCode = con.getResponseCode();
	          //System.out.println("POST Response Code :: " + responseCode);

	          if (responseCode == HttpURLConnection.HTTP_OK) { //success
	            BufferedReader in = new BufferedReader(new InputStreamReader(
	                con.getInputStream()));
	            String inputLine;


	            while ((inputLine = in.readLine()) != null) {
	              response.append(inputLine);
	            }
	            in.close();

	            // print result
	            System.out.println(response.toString());
	          } else {
	           System.out.println("POST request not worked");
	          }
	          return response.toString();
	        }

}


