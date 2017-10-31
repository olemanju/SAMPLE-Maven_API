package com.wip.test;

import static com.jayway.restassured.RestAssured.given;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.Date;

import com.jayway.restassured.response.Response;
import com.mongodb.util.JSON;

//import lib.cisco.util.WrapperActionTest;

public class WebserviceGETPostMan 
{
	
	public static String getRequestWithoutToken_SSLRelaxed(String url, String name, String pwd, String token) {
		try{
			String body = "";
			//Date strInitialTimeStamp=WrapperActionTest.getTimeStamp();
			//if it returns null what will happen
			Response response =given()
					.relaxedHTTPSValidation()
					.authentication().basic(name, pwd)
					.headers("Content-Type", "application/json", "Authorization", "Basic ZWJmYXBpZGV2LmdlbjplYmZhcGlkZXY=")
					.when()
					.get(url);
		/*	Date strEndTimeStamp=WrapperActionTest.getTimeStamp();
			long SeondsDiff = ((strEndTimeStamp.getTime() - strInitialTimeStamp.getTime())/1000); 
			long difference=(strEndTimeStamp.getTime() - strInitialTimeStamp.getTime());
			WrapperActionTest.strTimeDifference="Time Before Response is <b>"+strInitialTimeStamp+"</b>--Time After Response is Finished is <b>"+strEndTimeStamp+"</b> and Time Taken for the Response to generate is <b>"+difference+" Milli Seconds(Approx)</b> i.e., <b>"+SeondsDiff+" Seconds(Approx)</b>"; 
*/
			body  = response.getBody().asString();
			System.out.println("==Response==" + body);

			return body;

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	public static void main(String[] args) 
	{
		
		boolean rc=false;
		String strparamURL=null;
		String responseBody=null;
		
		String strReqURl="https://eb-api-stage.cisco.com/users/v1.0/";
		strparamURL="hnagabhy_test";
		
		responseBody=getRequestWithoutToken_SSLRelaxed(strReqURl+strparamURL, "", "", "");
		
		if(responseBody.contains("Not Authorized"))
		{

			//logMessage("Validate the Response", "Response should be Not Authorized ", "Unable to fetch the response with response body message '"+responseBody.toString()+"'", "Passed");      
			System.out.println("Unable to fetch the response with response body message '"+responseBody.toString()+"");
		}

		else if((responseBody!=null)&&(!responseBody.equalsIgnoreCase(""))&&(!responseBody.contains("System is experiencing issues, please try again"))&&(!responseBody.contains("Not Authorized")))
		{
			
		//	logMessage("Validate the Response", "Response should not be null", "Able to fetch the Response", "Passed"); 
			System.out.println("Able to fetch the Response '"+responseBody.toString()+"");
		}
		else
		{ 

			//logMessage("Validate the Response", "Response should not be null", "Unable to fetch the response with response body message '"+responseBody.toString()+"'", "Failed"); 
			System.out.println("Unable to fetch the response with response body message '"+responseBody.toString()+"");	
					}
		
		verifyJsonResponse( responseBody);
		
		

	}
	
	public static String verifyJsonResponse(String jsonresponse)
	{
		String response=null;
		try 
		{
			
		//	System.out.println("Print Raw Response " + jsonresponse);
			JSONObject jsonobject=(JSONObject) JSONValue.parseWithException(jsonresponse);
	
			//System.out.println("Print Json Resoponse " + jsonobject);
			
			//Print only Data inside the Data 
				System.out.println("Print only Data Content " + jsonobject.get("data"));
	
				//Print only userCountCI
			//	System.out.println("Print count CCI " + jsonobject.get("userCountCI"));
				
				//Print only Status
				System.out.println("Print status " + jsonobject.get("status"));
				String Status=null;
				//Status=jsonobject.get("status").toString();
				Status=(String)jsonobject.get("status");
				System.out.println("Status is " + Status);
				//Print only Support Email
			//	System.out.println("Support email " + jsonobject.get("message"));
				//Print only userCountCI
			//	System.out.println("Print count CCI " + jsonobject.get("code"));
				
				//Print only Status
				//System.out.println("Print status " + jsonobject.get("supportEmail"));
				
				//Print only Support Email
			//	System.out.println("Support email " + jsonobject.get("timeStamp"));
				if (jsonobject.get("status").toString().equalsIgnoreCase("success"))
				{
					System.out.println("Status is success");
				}
				else
				{
					System.out.println("Status is not Success");
				}
				
				JSONArray jsarr= (JSONArray) jsonobject.get("data");
				
			for(int i=0; i<jsarr.size(); i++)
			{
				
				JSONObject jarrayobj= (JSONObject)jsarr.get(i);
				
				String strebUserId=null;
				strebUserId=(String)jarrayobj.get("ebUserId");
				System.out.println("After Type casting to String " + strebUserId);
				
				if(strebUserId.equals("hnagabhy_tesT"))
				{
					System.out.println("Both the Strings are correct");
				}
				else
				{
					System.out.println("Both the Strings are not correct");
				}
				
				String strphone=null;
				strphone=(String)jarrayobj.get("phone");
				System.out.println("After Type casting to String Phone Number is " + strphone);
			}
				
			return response;
		} catch (Exception e)
		{
				System.out.println("Exception occured" + e.getLocalizedMessage());
				//return false;
		}
		return null;
	}

}