package unibo.wisfacade;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.msg.ApplMessage;
import unibo.basicomm23.utils.CommUtils;

@RestController
@RequestMapping(path = "/RestApi") //, produces = "application/json"
public class M2MController {
    public static M2MController m2mCtrl;
    private ApplguiCore guiCore;
    private String answer = null;
    protected String mainPage   = "basicrobot23EssentialGui"; //"basicrobot23Gui";

    public M2MController(){
        m2mCtrl = this;
        this.guiCore = FacadeBuilder.guiCore;
        CommUtils.outgreen (" --- M2MController | STARTS guiCore=" + guiCore);
    }

    //Injiection
    public void setGuiCore(ApplguiCore gui) {
        guiCore = gui;
        CommUtils.outgreen (" --- M2MController | injected guiCore=" + guiCore);
    }

    public synchronized void setAnswer(String answer){
        this.answer = answer;
    }

    @GetMapping("/")
    public String entry(Model viewmodel) {
        //Connection.trace = true;

        return mainPage;
    }
}
