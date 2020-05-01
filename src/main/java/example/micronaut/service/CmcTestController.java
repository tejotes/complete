package example.micronaut.service;


import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/cmctest")
public class CmcTestController {

    private final CmcTestService cmcTestService;

    public CmcTestController(CmcTestService cmcTestService) {
        this.cmcTestService = cmcTestService;
    }

    @Get
    String quote(){
        return cmcTestService.quote();
    }
}
