package main.java.unibo.WISFacade_Sprint3;

import org.json.JSONException;

import org.json.JSONObject;
import unibo.basicomm23.utils.CommUtils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class QaksysConfigSupport {

    public static List<String> readConfig( String fName )  {
    	Resource resource = new ClassPathResource(fName);
        try{
        	InputStream inputStream = resource.getInputStream();
        	String data = new String(inputStream.readAllBytes());
            return readTheContent(data);
        }catch( Exception e){
            try {
                CommUtils.outmagenta("QaksysConfigSupport | readConfig ERROR:" + e.getMessage());
                Path p = Paths.get("../"+fName);
                String data = null;
                data = new String( Files.readAllBytes(p));
                return readTheContent(data);
            } catch (Exception e1) {
                CommUtils.outred("QaksysConfigSupport | readConfig ERROR AGAIN:" + e.getMessage());
                //e1.printStackTrace();
            }
        }
        return null;

        /*
        try{
            String data = new String( Files.readAllBytes(Paths.get(fName)));
            return readTheContent(data);
        }catch( Exception e){
            CommUtils.outred("QaksysConfigSupport | readConfig ERROR:" + e.getMessage());
        }
        return null;

         */
    }

   protected static List<String> readTheContent( String config ) throws JSONException {
       CommUtils.outyellow("qaksysConfigSupport | readTheContent " + config);
       JSONObject jsonObject = new JSONObject(config); //jsonParser.parse(config) ;
       String host = jsonObject.get("host").toString();
       String port = jsonObject.get("port").toString();
       String context = jsonObject.get("context").toString();
       String actorfacade = jsonObject.get("facade").toString();
       String facadeport = jsonObject.get("facadeport").toString();
       String sysname = jsonObject.get("sysdescr").toString();

       List<String> outS = new ArrayList<String>();
       outS.add(host);
       outS.add(port);
       outS.add(context);
       outS.add(actorfacade);
       outS.add(facadeport);
       outS.add(sysname);
       CommUtils.outyellow("qaksysConfigSupport | readTheContent " + outS.toString());
       return outS;
    }

}
