package com.foulkes.lights.mvc.routing.homeauto.annotation.lights.router;

import com.foulkes.lights.common.events.HueComponentEvent;
import com.foulkes.lights.common.types.Hue;
import com.foulkes.lights.common.types.LightLevel;
import com.foulkes.lights.common.enums.EventState;
import com.foulkes.lights.common.enums.GenericType;
import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.model.ComponentsModel;
import com.foulkes.lights.mvc.routing.factory.ProcessEvent;
import com.foulkes.lights.common.events.ComponentEvent;
import com.foulkes.lights.mvc.routing.homeauto.annotation.light.payloads.HueLightResponse;
import com.foulkes.lights.mvc.routing.homeauto.annotation.lights.LightHueTransformerTest;
import com.foulkes.lights.mvc.routing.homeauto.annotation.payloads.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by danfoulkes on 06/02/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/routing-context.xml")
public class HueTest {



    @Autowired
    private ProcessEvent processEvent;
    @Autowired
    private LightHueTransformerTest lightHueTransformerTest;



    private HueLightResponse HueResponse(Hue hue, LightLevel lightLevel, ComponentsModel componentsModel, EventState eventState){
        HueLightResponse hueLightResponse = new HueLightResponse(hue, lightLevel, componentsModel);
        hueLightResponse.setEventState(eventState);
        hueLightResponse.setLightLevel(lightLevel);
        hueLightResponse.setHue(hue);
        return hueLightResponse;
    }

    @Test
    public void hueOffTest() {
        lightHueTransformerTest.setup(HueResponse(null,null,getHue(),EventState.OFF));
        Response result = processEvent.processComponentEvent(getEventChange(getHue(),EventState.OFF));
        if(result instanceof HueLightResponse){
            HueLightResponse response = (HueLightResponse) result;
            assertEquals(response.getEventState(), EventState.OFF);
        }
        else{
            fail("incorrect response type received.");
        }

    }

    @Test
    public void hueOnTest() {
        lightHueTransformerTest.setup(HueResponse(null,new LightLevel(255),getHue(),EventState.ON));
        Response result = processEvent.processComponentEvent(getEventChange(getHue(), EventState.ON));
        if(result instanceof HueLightResponse){
            HueLightResponse response = (HueLightResponse) result;
            assertEquals(response.getEventState(), EventState.ON);
        }
        else{
            fail("incorrect response type received.");
        }
    }

    @Test
    public void LightTest() {

        Hue hue = new Hue(1000);
        LightLevel lightLevel = new LightLevel(255);
        lightHueTransformerTest.setup(HueResponse(hue, lightLevel,getHue(),EventState.STATUS));
        Response result = processEvent.processComponentEvent(getEventChange(getHue(), EventState.STATUS));
        if(result instanceof HueLightResponse){
            HueLightResponse response = (HueLightResponse) result;
            assertEquals(response.getEventState(), EventState.STATUS);
            assertEquals(response.getComponentsModel().getUniquieId(), getHue().getUniquieId());
            assertEquals(response.getHue().getHue(),hue.getHue());
            assertEquals(response.getLightLevel().getLevel(),lightLevel.getLevel());
        }
        else{
            fail("incorrect response type received.");
        }
    }


    @Test
    public void changeBrightnessTest() {

        Hue hue = new Hue(1000);
        //one value below min
        LightLevel lightLevel = new LightLevel(1);
        lightHueTransformerTest.setup(HueResponse(hue, lightLevel,getHue(),EventState.ON));
        Response result = processEvent.processComponentEvent(getEventChange(getHue(), EventState.ON));
        if(result instanceof HueLightResponse){
            HueLightResponse response = (HueLightResponse) result;
            assertEquals(response.getEventState(), EventState.ON);
            assertEquals(response.getComponentsModel().getUniquieId(), getHue().getUniquieId());
            assertEquals(response.getHue().getHue(),hue.getHue());
            assertEquals(response.getLightLevel().getLevel(),lightLevel.getLevel());

            hue = response.getHue();
            lightLevel = response.getLightLevel();
            assertEquals(lightLevel.getLevel(), new Integer(1));

            //one value above max
            lightLevel.setLevel(255);

            result = processEvent.processComponentEvent(getEventChangeDim(getHue(),EventState.ON, lightLevel));
            response = (HueLightResponse) result;
            hue = response.getHue();
            lightLevel = response.getLightLevel();

            if(result instanceof HueLightResponse) {
                assertEquals(lightLevel.getLevel(), new Integer(255));
                assertEquals(hue.getHue(),hue.getHue());
            }
            else{
                fail("incorrect response type received.");
            }
        }
        else{
            fail("incorrect response type received.");
        }
    }


    @Test
    public void changeColoursTest() {

        Hue hue = new Hue(1000);
        //one value below min
        LightLevel lightLevel = new LightLevel(254);
        lightHueTransformerTest.setup(HueResponse(hue, lightLevel,getHue(),EventState.ON));
        Response result = processEvent.processComponentEvent(getEventChangeColour(getHue(), EventState.ON,hue));
        if(result instanceof HueLightResponse){
            HueLightResponse response = (HueLightResponse) result;
            assertEquals(response.getEventState(), EventState.ON);
            assertEquals(response.getComponentsModel().getUniquieId(), getHue().getUniquieId());
            assertEquals(response.getHue().getHue(),hue.getHue());
            assertEquals(response.getLightLevel().getLevel(),lightLevel.getLevel());

            hue = response.getHue();
            lightLevel = response.getLightLevel();
            assertEquals(lightLevel.getLevel(), new Integer(254));

            hue.setHue(500);

            result = processEvent.processComponentEvent(getEventChangeColour(getHue(),EventState.ON, hue));
            response = (HueLightResponse) result;
            hue = response.getHue();
            lightLevel = response.getLightLevel();

            if(result instanceof HueLightResponse) {
                assertEquals(lightLevel.getLevel(), new Integer(254));
                assertEquals(hue.getHue(),500,0);
            }
            else{
                fail("incorrect response type received.");
            }
        }
        else{
            fail("incorrect response type received.");
        }
    }



    private ComponentEvent  getEventChange(ComponentsModel model, EventState state){
        HueComponentEvent componentEvent = new HueComponentEvent(model.getIp(), state, ServiceTypes.LIGHT_HUE, GenericType.LIGHT, model.getAddressDetails());
        return componentEvent;
    }

    private ComponentEvent getEventChangeDim(ComponentsModel model, EventState state, LightLevel lightLevel){
        HueComponentEvent componentEvent = new HueComponentEvent(model.getIp(), state, ServiceTypes.LIGHT_HUE, GenericType.LIGHT, model.getAddressDetails(),lightLevel);
        return componentEvent;
    }

    private ComponentEvent getEventChangeColour(ComponentsModel model, EventState state, Hue hue){
        HueComponentEvent componentEvent = new HueComponentEvent(model.getIp(), state, ServiceTypes.LIGHT_HUE, GenericType.LIGHT, model.getAddressDetails(),hue);
        return componentEvent;
    }

    private ComponentsModel getHue(){
        ComponentsModel wemo = new ComponentsModel();
        wemo.setComponentType(ServiceTypes.LIGHT_HUE);
        wemo.setGenericType(GenericType.LIGHT);
        wemo.setIp("192.168.1.100");
        wemo.setAddressDetails("usn:123-456-789");
        wemo.setAssigned(false);
        wemo.setUniquieId("usn:123-456-789");
        return wemo;
    }

}
