package com.wip.test;

import static com.jayway.restassured.RestAssured.given;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.restassured.response.Response;

public class Rest_GetAll_Countrydetails {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		
		String strReqURl="http://services.groupkt.com/country/get/all";
		//strparamURL="hnagabhy_test";
		String strparamURL=null;
		String responseBody=null;
		
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
		
		verifyJasonResponse_new(responseBody);
	}
	
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
		int size= elasticHitsArray_allHits.size();
		System.out.println("Size is " + size);
		Integer Totalnumber=0;
		
		for(int i=0; i < elasticHitsArray_allHits.size(); i++)
		{
			String strLoopMessage= elasticHitsArray_allHits.get(i).getAsString();
			System.out.println("messages " + strLoopMessage);
			
			
					String[] userIdInLowerCase = strLoopMessage.split(" ");
					
					/*for(String s: userIdInLowerCase)
					{
						System.out.println("After break " + s);
						strLoopMessage = userIdInLowerCase[1];
					}*/
					strLoopMessage=userIdInLowerCase[1];
					strLoopMessage = strLoopMessage.replaceAll("\\[", "").replaceAll("\\]","");
					System.out.println( "after Removal of Square brakets " + strLoopMessage);
					
					 Totalnumber=Integer.valueOf(strLoopMessage);
					System.out.println("after Conversion "+ Totalnumber);
		}
		
		JsonElement result = jobresponse.get("RestResponse").getAsJsonObject().get("result");
        
        System.out.println("Result is " + result);
        

        JsonArray jsonarrayResults = jobresponse.get("RestResponse").getAsJsonObject().get("result").getAsJsonArray();
        
        System.out.println("Name  is " + jsonarrayResults);
        
      
       // JsonArray elasticHitsArray_allHits = elasticJsonResponse.get("hits").getAsJsonObject().get("hits").getAsJsonArray();
        Integer counter=0;
        for (int i = 0; i <jsonarrayResults.size(); i++) 
        {
              try 
              {
                    JsonObject json_CurrentHit = jsonarrayResults.get(i).getAsJsonObject();
                    counter++;
                  //  String Strname = jobresponse.get("RestResponse").getAsJsonObject().get("result").getAsJsonObject().get("name").getAsString();
                    String Strname = json_CurrentHit.get("name").getAsString();
                    System.out.println("Strname " + Strname);
                    
                    JsonElement jsonalpha2_code = json_CurrentHit.get("alpha2_code");
                    System.out.println("jsonalpha2_code " + jsonalpha2_code);
                    
                    JsonElement alpha3_code = json_CurrentHit.get("alpha3_code");
                   // System.out.println("alpha3_code before converting to String" + alpha3_code);
                    
                    String alpha3_code_con= alpha3_code.getAsString();
                    System.out.println("after conversion " + alpha3_code_con);
                    
              if(Strname.equalsIgnoreCase("india"))
              {
            	  System.out.println("I am from India");
              }
              

              }
              catch (Exception e) {
                    System.out.println("Error in  Record No "+i+"");
                    e.printStackTrace();
              }
        }
        
        System.out.println("Total Counter is " + counter);
        
        if(Totalnumber.equals(counter))
        {
        	System.out.println("Both the counts are same");
        }
        else
        {
        	System.out.println("Both the counts are not same");
        }

		return jsonresponse1;

}
}
