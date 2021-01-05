package com.tsoft.bot.frontend.steps.WebDelivery;

import com.tsoft.bot.frontend.pages.pages.P_Corporativo;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class Corporativo {
    public WebDriver driver;
    P_Corporativo Corporativo = new P_Corporativo(driver);
    @Given("^INGRESAMOS A LA URL WEB DELIVERY \"([^\"]*)\"$")
    public void ingresamosALAURLWEBDELIVERY(String dato) throws Throwable {
        Corporativo.ingresamosALAURLWEBDELIVERY(dato);
    }
    @When("^INGRESAMOS USUARIO WEB DELIVERY\"([^\"]*)\"$")
    public void ingresamosUSUARIOWEBDELIVERY(String dato) throws Throwable {
        Corporativo.ingresamosUSUARIOWEBDELIVERY(dato);
    }

    @And("^INGRESAMOS CONTRASEÑA WEB DELIVERY\"([^\"]*)\"$")
    public void ingresamosCONTRASEÑAWEBDELIVERY(String dato) throws Throwable {
        Corporativo.ingresamosCONTRASEÑAWEBDELIVERY(dato);
    }
    @Then("^CLICK BOTON LOGIN WEB DELIVERY Y SE INGRESA CORRECTAMENTE$")
    public void clickBOTONLOGINWEBDELIVERYYSEINGRESACORRECTAMENTE() throws Throwable{
        Corporativo.clickBOTONLOGINWEBDELIVERYYSEINGRESACORRECTAMENTE();
    }

    @Given("^SELECCIONAR ASIGNACIÓN DE SERIES\\(CORPORATIVO\\)$")
    public void seleccionarASIGNACIÓNDESERIESCORPORATIVO()throws Throwable {
        Corporativo.seleccionamosAsignaciónDeSeriesCorporativo();
    }

    @When("^BUSCAR EL ID DE RESERVA\\(CORPORATIVO\\)\"([^\"]*)\"$")
    public void buscarELIDDERESERVACORPORATIVO(String dato) throws Throwable {
       Corporativo.buscamosElIdDeReservaCorporativo(dato);
    }

    @And("^INGRESAR MATERIALES\\(CORPORATIVO\\) \"([^\"]*)\"$")
    public void ingresarMATERIALESCORPORATIVO(String dato) throws Throwable {
        Corporativo.ingresamosMaterialesIMEIYSIMCARD(dato);
    }

    @And("^VALIDAR SERIES\\(CORPORATIVO\\)$")
    public void validarSERIESCORPORATIVO() throws Throwable {
        Corporativo.validamosSeriesCorporativo();
    }

    @Then("^VERFICIAR EL ESTADO DE LAS SERIES\\(CORPORATIVO\\)\"([^\"]*)\"$")
    public void verficiarELESTADODELASSERIESCORPORATIVO(String dato) throws Throwable {
        Corporativo.verificarElEstadoDeAsignaciónDeSerieCorporativos(dato);
    }
}
