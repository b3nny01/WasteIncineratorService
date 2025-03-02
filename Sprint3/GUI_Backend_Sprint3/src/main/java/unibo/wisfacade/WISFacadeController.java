package unibo.wisfacade;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import unibo.basicomm23.utils.CommUtils;

 

@Controller
public class WISFacadeController {
    protected String sysName = "unknown";
    public final static String robotName  = "wis";
    //Settaggio degli attributi del modello VEDI application.properties
    //@Value("${robot24.protocol}")
    String protocol="tcp";
    //@Value("${robot24.webcamip}")
    String webcamip;
 
    @Value("${spring.application.name}")
    String appName;  //vedi application.properties

    @Value("${robot24.robotip}")
    String robotip;
    
    @Value("${robot24.plantodo}")
    String plantodo;
    
    @Value("${robot24.plandone}")
    String plandone;
    
    @Value("${robot24.steptime}")    
    String steptime;
    
    @Value("${robot24.map}")    
    String map;

    @Value("${robot24.robotpos}")    
    String robotpos;

    protected String mainPage     = "index";
    protected ApplguiCore guiCore ;

    public WISFacadeController(){
        CommUtils.outgreen (" --- WisController | STARTS "  + steptime);
        new FacadeBuilder( ) ;
        guiCore = FacadeBuilder.guiCore;
    }

    protected String buildThePage(Model viewmodel) {
        setConfigParams(viewmodel);
        return mainPage;
    }
    protected void setConfigParams(Model viewmodel){
    	CommUtils.outblack("WisSprint3Controller | setConfigParams plandone="+ plandone);
        viewmodel.addAttribute("robotip",  robotip);
        viewmodel.addAttribute("steptime", steptime);
        viewmodel.addAttribute("plandone", plandone);
        viewmodel.addAttribute("map", map);
        viewmodel.addAttribute("robotpos", robotpos);

//      viewmodel.addAttribute("protocol", protocol);
//      viewmodel.addAttribute("webcamip", webcamip);
//      viewmodel.addAttribute("plantodo", plantodo);
    }
 

    @GetMapping("/")
    public String homePage(Model viewmodel) {
        viewmodel.addAttribute("appname", ApplSystemInfo.appName);
        String dir = System.getProperty("user.dir");
        CommUtils.outgreen (" --- WisSprint3Controller | entry dir= "+dir  );
        return buildThePage(viewmodel);
    }

    @ExceptionHandler
    public ResponseEntity handle(Exception ex) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity(
             "BaseController ERROR " + ex.getMessage(),
             responseHeaders, HttpStatus.CREATED);
    }

}
/*
 * curl --location --request POST 'http://localhost:8080/move' --header 'Content-Type: text/plain' --form 'move=l'
 * curl -d move=r localhost:8080/move
 */