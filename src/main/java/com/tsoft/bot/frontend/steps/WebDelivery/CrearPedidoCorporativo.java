package com.tsoft.bot.frontend.steps.WebDelivery;

import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.pages.CorporativoCrearPedido;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Th;
import org.openqa.selenium.WebDriver;

public class CrearPedidoCorporativo {

    public WebDriver driver;
    CorporativoCrearPedido createCorPed = new CorporativoCrearPedido();


    public CrearPedidoCorporativo() {
        this.driver = Hook.getDriver();
    }
    @Given("^click en crear pedido$")
    public void clickEnCrearPedido() throws  Throwable{
    createCorPed.clickEnCrearPedido();
    }

    @When("^Ingresar y buscar el número de RUC \"([^\"]*)\"$")
    public void ingresarYBuscarElNúmeroDeRUC(String casoPrueba) throws Throwable {
        createCorPed.ingresarYBuscarElNúmeroDeRUC(casoPrueba);
    }
    @And("^Ingresar el tipo de pedido y almacén \"([^\"]*)\"$")
    public void ingresarElTipoDePedidoYAlmacén(String casoPrueba) throws Throwable {
        createCorPed.ingresarElTipoDePedidoYAlmacén(casoPrueba);
    }
    @And("^Infromación del solicitante\"([^\"]*)\"$")
    public void infromaciónDelSolicitante(String casoPrueba) throws Throwable {
        createCorPed.infromaciónDelSolicitante(casoPrueba);
    }

    @And("^Dirección de entrega$")
    public void direcciónDeEntrega() throws  Throwable {
        createCorPed.direcciónDeEntrega();

    }

    @And("^Información del receptor \"([^\"]*)\"$")
    public void informaciónDelReceptor(String casoPrueba) throws Throwable {
      createCorPed.informaciónDelReceptor(casoPrueba);
    }

    @Then("^click en botón continuar$")
    public void clickEnBotónContinuar()  throws Throwable {
        createCorPed.clickEnBotónContinuar();

    }
    @Given("^Click botón fila nueva$")
    public void clickBotónFilaNueva() throws Exception {
        createCorPed.clickBotónFilaNueva();

    }

    @When("^Linea de detalle de solicitud \\(Alta\\)\"([^\"]*)\"$")
    public void lineaDeDetalleDeSolicitudAlta(String casoPrueba) throws Throwable {
        createCorPed.lineaDeDetalleDeSolicitudAlta(casoPrueba);
    }

    @And("^Click botón consultar disponibilidad$")
    public void clickBotónConsultarDisponibilidad() throws Exception {
        createCorPed.clickBotónConsultarDisponibilidad();

    }
    @And("^Click botón realizar reserva$")
    public void clickBotónRealizarReserva() throws Exception {
        createCorPed.clickBotónRealizarReserva();
    }

    @And("^Click botón generar detalles del pedido$")
    public void clickBotónGenerarDetallesDelPedido() throws Exception {
        createCorPed.clickBotónGenerarDetallesDelPedido();
    }

    @And("^Click botón continuar$")
    public void clickBotónContinuar() throws Exception {
        createCorPed.clickBotónContinuar();

    }
    @And("^Click botón continuar siguiente$")
    public void clickBotónContinuarSiguiente() throws Exception {
       createCorPed.clickBotónContinuarPaso();
    }

    @And("^Click botón enviar$")
    public void clickBotónEnviar() throws Throwable {
      createCorPed.clickBotónEnviar();
    }
    @Then("^Guardar el código de pedido \"([^\"]*)\"$")
    public void guardarElCódigoDePedido(String casoPrueba) throws Throwable {
        createCorPed.guardarElCódigoDePedido(casoPrueba);
    }







}
