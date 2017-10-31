package com.wip.test;

import static com.jayway.restassured.RestAssured.given;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.restassured.response.Response;

public class RestassuredgettheCountry {

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
		
		String strReqURl="http://services.groupkt.com/country/get/iso2code/IN";
		//strparamURL="hnagabhy_test";
		
		responseBody=getRequestWithoutToken_SSLRelaxed(strReqURl, "", "", "");
		
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
		
		//verifyJsonResponse( responseBody);
		verifyJasonResponse_new(responseBody);
		

	}
	
	public static String verifyJasonResponse_new(String jsonresponse1)
	{
		
		
		//New code
		JsonParser jsonParser= new JsonParser();
		String strAllBatches = jsonresponse1;
		
		JsonObject jobresponse= jsonParser.parse(strAllBatches).getAsJsonObject();
		System.out.println("response json: "+jobresponse);
		
		JsonElement strRestResponse = jobresponse.get("RestResponse");
		System.out.println("RestResponse is  " + strRestResponse);
		
		
		JsonArray elasticHitsArray_allHits = jobresponse.get("RestResponse").getAsJsonObject().get("messages").getAsJsonArray();
		
		System.out.println("elasticHitsArray_allHits  " + elasticHitsArray_allHits);
		
		JsonElement result = jobresponse.get("RestResponse").getAsJsonObject().get("result");
        
        System.out.println("Result is " + result);
        

        String Strname = jobresponse.get("RestResponse").getAsJsonObject().get("result").getAsJsonObject().get("name").getAsString();
        
        System.out.println("Name  is " + Strname);
        
        String stralpha2_code = jobresponse.get("RestResponse").getAsJsonObject().get("result").getAsJsonObject().get("alpha2_code").getAsString();
        
        System.out.println("stralpha2_code  is " + stralpha2_code);
        
        String stralpha3_code = jobresponse.get("RestResponse").getAsJsonObject().get("result").getAsJsonObject().get("alpha3_code").getAsString();
        
        System.out.println("alpha3_code  is " + stralpha3_code);
		/*
        JsonArray elasticHitsArray_allHits = elasticJsonResponse.get("hits").getAsJsonObject().get("hits").getAsJsonArray();
        
        for (int i = 0; i < elasticHitsArray_allHits.size(); i++) 
        {
              try 
              {
                    JsonObject json_CurrentHit = elasticHitsArray_allHits.get(i).getAsJsonObject();
                    JsonElement jEleme_BatchId = json_CurrentHit.get("_source").getAsJsonObject().get("batchId");
                    if (jEleme_BatchId == null)
                    {
                          System.out.println(i+" batchId not found in "+json_CurrentHit);
                          continue;
                    }
                    log("jEleme_BatchId : "+jEleme_BatchId.getAsString());
                    set_BatchIds.add(jEleme_BatchId.getAsString());

              }
              catch (Exception e) {
                    System.out.println("Error in  Record No "+i+"");
                    e.printStackTrace();
              }
        }*/

		return jsonresponse1;
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
				System.out.println("Print only Data Content " + jsonobject.get("result"));
	
				//Print only userCountCI
			//	System.out.println("Print count CCI " + jsonobject.get("userCountCI"));
				
				//Print only Status
				//System.out.println("Print status " + jsonobject.get("status"));
				String Status=null;
				Status=jsonobject.get("result").toString();
				//Status=(String)jsonobject.get("result");
				System.out.println("Status is " + Status);
				//Print only Support Email
			//	System.out.println("Support email " + jsonobject.get("message"));
				//Print only userCountCI
			//	System.out.println("Print count CCI " + jsonobject.get("code"));
				
				//Print only Status
				//System.out.println("Print status " + jsonobject.get("supportEmail"));
				
				//Print only Support Email
			//	System.out.println("Support email " + jsonobject.get("timeStamp"));
			/*	if (jsonobject.get("status").toString().equalsIgnoreCase("success"))
				{
					System.out.println("Status is success");
				}
				else
				{
					System.out.println("Status is not Success");
				}*/
				
				JSONArray jsarr= (JSONArray) jsonobject.get("RestResponse");
				
			for(int i=0; i<jsarr.size(); i++)
			{
				
				JSONObject jarrayobj= (JSONObject)jsarr.get(i);
				
				String strname=null;
				strname=(String)jarrayobj.get("name");
				System.out.println("After Type casting to String " + strname);
				
				if(strname.equals("Ind"))
				{
					System.out.println("Both the Strings are correct");
				}
				else
				{
					System.out.println("Both the Strings are not correct");
				}
				
				String stralpha2_code=null;
				stralpha2_code=(String)jarrayobj.get("alpha2_code");
				System.out.println("After Type casting to String Phone Number is " + stralpha2_code);
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
