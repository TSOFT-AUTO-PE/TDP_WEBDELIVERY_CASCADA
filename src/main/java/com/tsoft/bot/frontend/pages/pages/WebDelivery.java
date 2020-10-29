package com.tsoft.bot.frontend.pages.pages;

import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.CargaMateriales;
import com.tsoft.bot.frontend.pages.objects.Corporativo;
import com.tsoft.bot.frontend.pages.objects.ExcelWebDelivery;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.ExtentReportUtil;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.CharArrayReader;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WebDelivery {
    ExcelWebDelivery exCreatePed = new ExcelWebDelivery();

    public WebDriver driver;
    String step = "";
    static GenerateWord generateWord = new GenerateWord();

    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(exCreatePed.EXCEL_WEB, exCreatePed.ORDEN);
    }

    public WebDelivery() {
        this.driver = Hook.getDriver();
    }
    String PEDIDO;
    public void ingresoALaUrlDeWEBDELIVERY(String casoDePrueba) throws Throwable {
        try {

            int LoginWD = Integer.parseInt(casoDePrueba) - 1;
            String url = getData().get(LoginWD).get(exCreatePed.COLUMNA_URL);
            driver.get(url);

            ExtentReportUtil.INSTANCE.stepPass(driver, "Se cargó correctamente la página");
            generateWord.sendText("Carga correcta de la página");
            generateWord.addImageToWord(driver);
            generateWord.sendBreak();
            System.out.println(ImagePath.getBundlePath());


        }catch (Exception e){
            ExcelReader.writeCellValue(exCreatePed.EXCEL_WEB, exCreatePed.ORDEN, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }

    public void ingresoElUsuarioDeWEBDELIVERY(String casoDePrueba) throws Throwable {

        try {
            int user = Integer.parseInt(casoDePrueba) - 1;
            String usuario = getData().get(user).get(exCreatePed.COLUMNA_USUARIO);
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.TXT_USER));
            if (driver.findElement(Corporativo.TXT_USER).isDisplayed()){
                driver.findElement(Corporativo.TXT_USER).clear();
                driver.findElement(Corporativo.TXT_USER).sendKeys(usuario);
            }
            ExtentReportUtil.INSTANCE.stepPass(driver, "Ingresamos el usuario");
            generateWord.sendText("Ingresamos el usuario");
            generateWord.addImageToWord(driver);

        }catch (Exception e){
            ExcelReader.writeCellValue(exCreatePed.EXCEL_WEB, exCreatePed.ORDEN, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }


    public void laContraseñaDeWEBDELIVERY(String casoDePrueba) throws Throwable {

        try {
            int PASS = Integer.parseInt(casoDePrueba) - 1;
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.TXT_PASSWORD));
            driver.findElement(Corporativo.TXT_PASSWORD).clear();
            String contra = getData().get(PASS).get(exCreatePed.COLUMNA_CONTRASENIA);
            driver.findElement(Corporativo.TXT_PASSWORD).sendKeys(contra);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Ingresamos la contraseña");
            generateWord.sendText("Ingresamos la contraseña");
            generateWord.addImageToWord(driver);

        }catch (Exception e){
            ExcelReader.writeCellValue(exCreatePed.EXCEL_WEB, exCreatePed.ORDEN, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void seDaClicEnElBotonLoginDeWEBDELIVERYIngresandoCorrectamente() throws Throwable {
        try {
            driver.findElement(Corporativo.BTN_LOGIN).click();
            Thread.sleep(2000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresa correctamente");
            generateWord.sendText("Se ingresa correctamente");
            generateWord.addImageToWord(driver);
        }catch (Exception e){
            ExcelReader.writeCellValue(exCreatePed.EXCEL_WEB, exCreatePed.ORDEN, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void clickEnCrearPedido() throws Exception {
        try {
            driver.findElement(Corporativo.LNK_CREAR_PEDIDO).click();
            Thread.sleep(3000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Crear Pedido");
            generateWord.sendText("Crear Pedido");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void ingresarYBuscarElNúmeroDeRUC(String casoDePrueba) throws Throwable {

        try {
            Thread.sleep(2000);
            driver.findElement(Corporativo.BTN_LUPA).click();
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.TXT_RUC));
            int user = Integer.parseInt(casoDePrueba) - 1;
            String usuario = getData().get(user).get(exCreatePed.NUM_RUC);
            driver.findElement(Corporativo.TXT_RUC).sendKeys(usuario);
            driver.findElement(Corporativo.TXT_RUC).sendKeys(Keys.ENTER);
            String f = driver.findElement(Corporativo.LNK_RUC).getText();
            while (!f.equals(usuario)) {
                Thread.sleep(1000);
                String g;
                g = driver.findElement(Corporativo.TXT_VACIO2).getText();
                if (g.equals("0 - 0 de 0")){
                    ExtentReportUtil.INSTANCE.stepPass(driver, "RUC no encontrado");
                    generateWord.sendText("RUC no encontrado");
                    generateWord.addImageToWord(driver);
                    driver.quit();
                }else{
                    f = driver.findElement(Corporativo.LNK_RUC).getText();
                }
            }
            ExtentReportUtil.INSTANCE.stepPass(driver, "Descripción de la empresa");
            generateWord.sendText("Descripción de la empresa");
            generateWord.addImageToWord(driver);
            driver.findElement(Corporativo.LNK_RUC).click();
            Thread.sleep(5000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Datos del RUC");
            generateWord.sendText("Datos del RUC");
            generateWord.addImageToWord(driver);

        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }
    public void ingresarElTipoDePedidoYAlmacén(String casoDePrueba) throws Throwable {

        try {
            Thread.sleep(3000);
            driver.findElement(Corporativo.BTN_LUPA2).click();
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.TXT_TIPO_ALMACEN));
            int pedido = Integer.parseInt(casoDePrueba) - 1;
            String tipo = getData().get(pedido).get(exCreatePed.TIPO);
            if (tipo.equals("CAMBIO")){
                driver.findElement(Corporativo.TXT_TIPO_ALMACEN).sendKeys("CAMBIO");
                driver.findElement(Corporativo.TXT_TIPO_ALMACEN).sendKeys(Keys.ENTER);
                Thread.sleep(3000);
            }
            if (tipo.equals("ALTA COMBO")|| tipo.equals("ALTA SOLO SIM")){
                driver.findElement(Corporativo.TXT_TIPO_ALMACEN).sendKeys("ALTA");
                driver.findElement(Corporativo.TXT_TIPO_ALMACEN).sendKeys(Keys.ENTER);
                Thread.sleep(3000);
            }

            ExtentReportUtil.INSTANCE.stepPass(driver, "Tipo de pedido");
            generateWord.sendText("Tipo de pedido");
            generateWord.addImageToWord(driver);
            driver.findElement(Corporativo.LNK_TIPO_PEDIDO_CAMBIO).click();
            Thread.sleep(3000);
            int SAL_ANT = Integer.parseInt(casoDePrueba) - 1;
            String SALANT = getData().get(SAL_ANT).get(exCreatePed.SALIDA_ANTICIPADA);
            if (SALANT.equals("SI")){
                driver.findElement(Corporativo.CHECK_SALIDA_ANTICIPADA).click();
            }
            driver.findElement(Corporativo.BTN_LUPA3).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.TXT_TIPO_ALMACEN));
            driver.findElement(Corporativo.TXT_TIPO_ALMACEN).sendKeys("PE10API7");
            driver.findElement(Corporativo.TXT_TIPO_ALMACEN).sendKeys(Keys.ENTER);
            Thread.sleep(5000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Almacén");
            generateWord.sendText("Almacén");
            generateWord.addImageToWord(driver);
            driver.findElement(Corporativo.LNK_TIPO_ALMACEN).click();
            Thread.sleep(3000);
            driver.findElement(Corporativo.BTN_LUPA8).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.TXT_TIPO_ALMACEN));
            int pedido2 = Integer.parseInt(casoDePrueba) - 1;
            String tipo2 = getData().get(pedido2).get(exCreatePed.TIPO_PAGO);
            if (tipo2.equals("PAGO EFECTIVO")){
                driver.findElement(Corporativo.TXT_TIPO_ALMACEN).sendKeys("PAGO EFECTIVO");
                driver.findElement(Corporativo.TXT_TIPO_ALMACEN).sendKeys(Keys.ENTER);
            }
            if (tipo2.equals("FINANCIADO")){
                driver.findElement(Corporativo.TXT_TIPO_ALMACEN).sendKeys("FINANCIADO");
                driver.findElement(Corporativo.TXT_TIPO_ALMACEN).sendKeys(Keys.ENTER);
            }
            if (tipo2.equals("OTROS")){
                driver.findElement(Corporativo.TXT_TIPO_ALMACEN).sendKeys("OTROS");
                driver.findElement(Corporativo.TXT_TIPO_ALMACEN).sendKeys(Keys.ENTER);
            }
            if (tipo2.equals("NO")){
                Thread.sleep(1000);
            }
            Thread.sleep(5000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Tipo de pago");
            generateWord.sendText("Tipo de pago");
            Thread.sleep(2000);
            driver.findElement(Corporativo.LNK_PAGO_EFECTIVO).click();
            Thread.sleep(3000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Datos del pedido completos");
            generateWord.sendText("Datos del pedido completos");
            generateWord.addImageToWord(driver);

        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }

    }
    public void infromaciónDelSolicitante(String casoDePrueba) throws Throwable {

        try {
            Thread.sleep(2000);
            driver.findElement(Corporativo.TXT_N_DOCUMENTO).click();
            driver.findElement(Corporativo.BTN_LUPA4).click();
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.TXT_TIPO_ALMACEN));
            int user = Integer.parseInt(casoDePrueba) - 1;
            String usuario = getData().get(user).get(exCreatePed.N_DOCUMENTO);
            driver.findElement(Corporativo.TXT_TIPO_ALMACEN).sendKeys(usuario);
            driver.findElement(Corporativo.TXT_TIPO_ALMACEN).sendKeys(Keys.ENTER);
            Thread.sleep(3000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Datos del solicitante");
            generateWord.sendText("Datos del solicitante");
            generateWord.addImageToWord(driver);
            driver.findElement(Corporativo.LNK_TIPO_PEDIDO_CAMBIO).click();
            Thread.sleep(3000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Información del solicitante completo");
            generateWord.sendText("Información del solicitante completo");
            generateWord.addImageToWord(driver);


        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }
    public void direcciónDeEntrega() throws Exception {
        try {
            Thread.sleep(2000);
            driver.findElement(Corporativo.BTN_LUPA5).click();
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.LNK_TIPO_PEDIDO_CAMBIO));
            ExtentReportUtil.INSTANCE.stepPass(driver, "Descripción de dirección");
            generateWord.sendText("Descripción de dirección");
            generateWord.addImageToWord(driver);
            driver.findElement(Corporativo.LNK_TIPO_PEDIDO_CAMBIO).click();
            Thread.sleep(3000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Dirección de entrega completo");
            generateWord.sendText("Dirección de entrega completo");
            generateWord.addImageToWord(driver);


        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }
    public void informaciónDelReceptor(String casoDePrueba) throws Throwable {

        try {
            Thread.sleep(2000);
            driver.findElement(Corporativo.TXT_N_DOCUMENTO2).click();
            driver.findElement(Corporativo.BTN_LUPA9).click();
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.TXT_TIPO_ALMACEN));
            int user = Integer.parseInt(casoDePrueba) - 1;
            String usuario = getData().get(user).get(exCreatePed.N_DOCUMENTO_RECEP);
            driver.findElement(Corporativo.TXT_TIPO_ALMACEN).sendKeys(usuario);
            driver.findElement(Corporativo.TXT_TIPO_ALMACEN).sendKeys(Keys.ENTER);
            Thread.sleep(3000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Información del contacto");
            generateWord.sendText("Información del contacto");
            generateWord.addImageToWord(driver);
            driver.findElement(Corporativo.LNK_TIPO_PEDIDO_CAMBIO).click();
            Thread.sleep(3000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Información del receptor completo");
            generateWord.sendText("Información del receptor completo");
            generateWord.addImageToWord(driver);


        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }
    public void clickEnBotónContinuar() throws Exception {

        try {
            Thread.sleep(2000);
            driver.findElement(Corporativo.BTN_CONTINUAR).click();
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.BTN_FILA_NUEVA));
            ExtentReportUtil.INSTANCE.stepPass(driver, "Datos del agendamiento");
            generateWord.sendText("Datos del agendamiento");
            generateWord.addImageToWord(driver);


        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }
    public void clickBotónFilaNueva() throws Exception {
        try {

            driver.findElement(Corporativo.BTN_FILA_NUEVA).click();
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated( Corporativo.BTN_LUPA7));
            ExtentReportUtil.INSTANCE.stepPass(driver, "Linea de detalle de solicitud");
            generateWord.sendText("Linea de detalle de solicitud");
            generateWord.addImageToWord(driver);


        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }
    public void lineaDeDetalleDeSolicitudAlta(String casoDePrueba) throws Throwable {

        try {

            int pedido = Integer.parseInt(casoDePrueba) - 1;
            String tipo = getData().get(pedido).get(exCreatePed.TIPO);
            if (tipo.equals("CAMBIO")){
                String cambio_2 = getData().get(0).get(exCreatePed.TIPO_CAMBIO);
                if (cambio_2.equals("SOLO SIM")  ){
                    driver.findElement(Corporativo.BTN_LUPA7).click();
                    WebDriverWait wait = new WebDriverWait(driver, 60);
                    wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.TXT_RUC));
                    int user3 = Integer.parseInt(casoDePrueba) - 1;
                    String usuario3 = getData().get(user3).get(exCreatePed.COD_SAP);
                    driver.findElement(Corporativo.TXT_RUC).sendKeys(usuario3);
                    driver.findElement(Corporativo.TXT_RUC).sendKeys(Keys.ENTER);
                    Thread.sleep(3000);
                    ExtentReportUtil.INSTANCE.stepPass(driver, "Descripción del código SAP del SimCard");
                    generateWord.sendText("Descripción del código SAP del SimCard");
                    generateWord.addImageToWord(driver);
                    driver.findElement(Corporativo.LNK_TIPO_PEDIDO_CAMBIO).click();
                    Thread.sleep(3000);
                    int pedido3 = Integer.parseInt(casoDePrueba) - 1;
                    String tipo3 = getData().get(pedido3).get(exCreatePed.CANT_LINEAS);
                    driver.findElement(Corporativo.TXT_CANTIDAD_SOLICITADA).sendKeys(tipo3);
                    Thread.sleep(2000);
                    ExtentReportUtil.INSTANCE.stepPass(driver, "Linea de detalle de solicitud completa");
                    generateWord.sendText("Linea de detalle de solicitud completa");
                    generateWord.addImageToWord(driver);
                }
                /*
                Thread.sleep(2000);
                driver.findElement(BTN_LUPA7).click();
                Thread.sleep(3000);
                int user = Integer.parseInt(casoDePrueba) - 1;
                String usuario = getData().get(user).get(COD_SAP);
                driver.findElement(TXT_RUC).sendKeys(usuario);
                driver.findElement(TXT_RUC).sendKeys(Keys.ENTER);
                Thread.sleep(3000);
                ExtentReportUtil.INSTANCE.stepPass(driver, "Descripción del código SAP");
                generateWord.sendText("Descripción del código SAP");
                generateWord.addImageToWord(driver);
                driver.findElement(LNK_TIPO_PEDIDO_CAMBIO).click();
                Thread.sleep(3000);
                int pedido2 = Integer.parseInt(casoDePrueba) - 1;
                String tipo2 = getData().get(pedido2).get(CANT_LINEAS);
                driver.findElement(TXT_CANTIDAD_SOLICITADA).sendKeys(tipo2);
                Thread.sleep(2000);
                ExtentReportUtil.INSTANCE.stepPass(driver, "Linea de detalle de solicitud completa");
                generateWord.sendText("Linea de detalle de solicitud completa");
                generateWord.addImageToWord(driver);*/
            }
            if (tipo.equals("ALTA COMBO")){
                Thread.sleep(2000);
                driver.findElement(Corporativo.BTN_LUPA10).click();
                WebDriverWait wait = new WebDriverWait(driver, 60);
                wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.TXT_RUC));
                int user2 = Integer.parseInt(casoDePrueba) - 1;
                String usuario2 = getData().get(user2).get(exCreatePed.COD_SAP_EQUIPO);
                driver.findElement(Corporativo.TXT_RUC).sendKeys(usuario2);
                driver.findElement(Corporativo.TXT_RUC).sendKeys(Keys.ENTER);
                Thread.sleep(3000);
                ExtentReportUtil.INSTANCE.stepPass(driver, "Descripción del código SAP del equipo");
                generateWord.sendText("Descripción del código SAP del equipo");
                generateWord.addImageToWord(driver);
                driver.findElement(Corporativo.LNK_TIPO_PEDIDO_CAMBIO).click();
                Thread.sleep(3000);
                driver.findElement(Corporativo.BTN_LUPA7).click();
                Thread.sleep(3000);
                int user3 = Integer.parseInt(casoDePrueba) - 1;
                String usuario3 = getData().get(user3).get(exCreatePed.COD_SAP);
                driver.findElement(Corporativo.TXT_RUC).sendKeys(usuario3);
                driver.findElement(Corporativo.TXT_RUC).sendKeys(Keys.ENTER);
                Thread.sleep(3000);
                ExtentReportUtil.INSTANCE.stepPass(driver, "Descripción del código SAP del SimCard");
                generateWord.sendText("Descripción del código SAP del SimCard");
                generateWord.addImageToWord(driver);
                driver.findElement(Corporativo.LNK_TIPO_PEDIDO_CAMBIO).click();
                Thread.sleep(3000);
                int pedido3 = Integer.parseInt(casoDePrueba) - 1;
                String tipo3 = getData().get(pedido3).get(exCreatePed.CANT_LINEAS);
                driver.findElement(Corporativo.TXT_CANTIDAD_SOLICITADA).sendKeys(tipo3);
                Thread.sleep(2000);
                ExtentReportUtil.INSTANCE.stepPass(driver, "Linea de detalle de solicitud completa");
                generateWord.sendText("Linea de detalle de solicitud completa");
                generateWord.addImageToWord(driver);
            }
            if (tipo.equals("ALTA SOLO SIM")){
                driver.findElement(Corporativo.BTN_LUPA7).click();
                WebDriverWait wait = new WebDriverWait(driver, 60);
                wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.TXT_RUC));
                int user3 = Integer.parseInt(casoDePrueba) - 1;
                String usuario3 = getData().get(user3).get(exCreatePed.COD_SAP);
                driver.findElement(Corporativo.TXT_RUC).sendKeys(usuario3);
                driver.findElement(Corporativo.TXT_RUC).sendKeys(Keys.ENTER);
                Thread.sleep(3000);
                ExtentReportUtil.INSTANCE.stepPass(driver, "Descripción del código SAP del SimCard");
                generateWord.sendText("Descripción del código SAP del SimCard");
                generateWord.addImageToWord(driver);
                driver.findElement(Corporativo.LNK_TIPO_PEDIDO_CAMBIO).click();
                Thread.sleep(3000);
                int pedido3 = Integer.parseInt(casoDePrueba) - 1;
                String tipo3 = getData().get(pedido3).get(exCreatePed.CANT_LINEAS);
                driver.findElement(Corporativo.TXT_CANTIDAD_SOLICITADA).sendKeys(tipo3);
                Thread.sleep(2000);
                ExtentReportUtil.INSTANCE.stepPass(driver, "Linea de detalle de solicitud completa");
                generateWord.sendText("Linea de detalle de solicitud completa");
                generateWord.addImageToWord(driver);
            }


        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }
    public void clickBotónConsultarDisponibilidad() throws Exception {
        try {
            Thread.sleep(2000);
            driver.findElement(Corporativo.BTN_SONSULTAR_DISPON).click();
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.BTN_ACEPTAR_MENS_SIST));
            ExtentReportUtil.INSTANCE.stepPass(driver, "Mensaje del sistema");
            generateWord.sendText("Mensaje del sistema");
            generateWord.addImageToWord(driver);
            driver.findElement(Corporativo.BTN_ACEPTAR_MENS_SIST).click();


        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }
    public void clickBotónRealizarReserva() throws Exception {
        try {
            Thread.sleep(3000);
            driver.findElement(Corporativo.BTN_REALIZAR_RESERVA).click();
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.BTN_ACEPTAR_MENS_SIST));
            ExtentReportUtil.INSTANCE.stepPass(driver, "Mensaje del sistema");
            generateWord.sendText("Mensaje del sistema");
            generateWord.addImageToWord(driver);
            driver.findElement(Corporativo.BTN_ACEPTAR_MENS_SIST).click();
            Thread.sleep(3000);

        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }
    public void clickBotónGenerarDetallesDelPedido() throws Exception {
        try {
            Thread.sleep(2000);
            driver.findElement(Corporativo.BTN_GENERAR_DET_PEDIDO).click();
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.BTN_ACEPTAR_MENS_SIST));
            ExtentReportUtil.INSTANCE.stepPass(driver, "Mensaje del sistema");
            generateWord.sendText("Mensaje del sistema");
            generateWord.addImageToWord(driver);
            driver.findElement(Corporativo.BTN_ACEPTAR_MENS_SIST).click();
            Thread.sleep(3000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Lineas de detalle de solicitud completa");
            generateWord.sendText("Lineas de detalle de solicitud completa");
            generateWord.addImageToWord(driver);

        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }
    public void clickBotónContinuar() throws Exception {
        driver.findElement(Corporativo.BTN_CONTINUAR2).click();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.BTN_CONTINUAR3));
        ExtentReportUtil.INSTANCE.stepPass(driver, "Lineas Amdocs");
        generateWord.sendText("Lineas Amdocs");
        generateWord.addImageToWord(driver);

    }
    public void clickBotónContinuarPaso() throws Exception {
        driver.findElement(Corporativo.BTN_CONTINUAR3).click();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.BTN_ENVIAR_SALIDA_ANTICIPADA));
        ExtentReportUtil.INSTANCE.stepPass(driver, "Datos del pedido");
        generateWord.sendText("Datos del pedido");
        generateWord.addImageToWord(driver);
    }
    public void clickBotónEnviar() throws Throwable {


        String SALANT = getData().get(0).get(exCreatePed.SALIDA_ANTICIPADA);
        if (SALANT.equals("SI")) {
            driver.findElement(Corporativo.BTN_ENVIAR_SALIDA_ANTICIPADA).click();
        }else{
            driver.findElement(Corporativo.BTN_ENVIAR).click();
        }
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.BTN_ACEPTAR_MENS_SIST));
        ExtentReportUtil.INSTANCE.stepPass(driver, "Mensaje del sistema");
        generateWord.sendText("Datos del pedido");
        generateWord.addImageToWord(driver);
        driver.findElement(Corporativo.BTN_ACEPTAR_MENS_SIST).click();
        Thread.sleep(2000);
        ExtentReportUtil.INSTANCE.stepPass(driver, "Solicitud enviada");
        generateWord.sendText("Solicitud enviada");
        generateWord.addImageToWord(driver);
        driver.findElement(Corporativo.BTN_VER_DETALLES).click();
        Thread.sleep(4000);


    }
    public void guardarElCódigoDePedido(String casoDePrueba) throws Throwable {

        try {
            int pedido = Integer.parseInt(casoDePrueba) ;

            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Corporativo.TXT_COD_PEDIDO));
            PEDIDO = driver.findElement(Corporativo.TXT_COD_PEDIDO).getAttribute("value");

            if (!PEDIDO.equals("")){
                //NUM_PEDIDO = PEDIDO;

                ExtentReportUtil.INSTANCE.stepPass(driver, "Código de pedido");
                generateWord.sendText("Código de pedido");
                generateWord.addImageToWord(driver);
                System.out.println("EL Numero del pedido es el siguiente:" + PEDIDO);
                ExcelReader.writeCellValue(exCreatePed.EXCEL_WEB,exCreatePed.ORDEN,pedido,14,PEDIDO);


            }else {
                ExtentReportUtil.INSTANCE.stepFail(driver, "No existe código de pedido");
                generateWord.sendText("No existe código de pedido");
                generateWord.addImageToWord(driver);
            }

        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);

        }
    }
    public void seDaClickEnElBotonIRAEnWEBDELIVERY(String arg0) throws Throwable {

        try {
            driver.findElement(CargaMateriales.LST_IR_A).click();
            ExtentReportUtil.INSTANCE.stepPass(driver, "IR A lista de pedidos");
        }catch (Exception e){
            ExcelReader.writeCellValue(exCreatePed.EXCEL_WEB, exCreatePed.ORDEN, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void seleccionarAjusteDeInventario() throws Exception {
        try {
            Actions act = new Actions(driver);
            act.moveToElement(driver.findElement(CargaMateriales.LNK_GESTION_PEDIDOS)).build().perform();
            Actions act2 = new Actions(driver);
            act2.moveToElement(driver.findElement(CargaMateriales.LNK_GESTION_INVENTARIOS)).build().perform();
            driver.findElement(CargaMateriales.LNK_AJUSTE_INVENTARIO).click();
            Thread.sleep(2000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Ajuste de inventarios");
            generateWord.sendText("Ajuste de inventarios");
            generateWord.addImageToWord(driver);
        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void clickEnElBotonNuevoRegistro() throws Exception {
        try {
            driver.findElement(CargaMateriales.BTN_NUEVO_REGISTRO).click();
            Thread.sleep(2000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Nuevo registro");
            generateWord.sendText("Nuevo registro");
            generateWord.addImageToWord(driver);

        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void seleccionamosElTipoABASTECIMIENTO(String arg0) throws Throwable {

        try {
            driver.findElement(CargaMateriales.BTN_TIPO).click();
            Thread.sleep(1000);
            generateWord.sendText("Click ABASTECIMIENTO");
            generateWord.addImageToWord(driver);
            driver.findElement(CargaMateriales.LNK_ABASTECIMIENTO).click();
            Thread.sleep(2000);
            String estado = driver.findElement(CargaMateriales.TXT_TIPO).getAttribute("value");
            if (estado.equals("ABASTECIMIENTO")){
                ExtentReportUtil.INSTANCE.stepPass(driver, "Tipo: ABASTECIMIENTO");
                generateWord.sendText("Tipo: ABASTECIMIENTO");
                generateWord.addImageToWord(driver);
            }else {
                ExtentReportUtil.INSTANCE.stepFail(driver, "No seleccionó ABASTECIMIENTO");
                generateWord.sendText("No seleccionó ABASTECIMIENTO");
                generateWord.addImageToWord(driver);
            }

        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void ingresamosUnComentario(String casoDePrueba) throws Throwable {

        try {
            int coment = Integer.parseInt(casoDePrueba) - 1;
            driver.findElement(CargaMateriales.TXT_COMENTARIO).clear();
            driver.findElement(CargaMateriales.TXT_COMENTARIO).sendKeys("PRUEBAS-QA");
            ExtentReportUtil.INSTANCE.stepPass(driver, "Ingresamos comentario");
            generateWord.sendText("Ingresamos comentario");
            generateWord.addImageToWord(driver);

        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void ingresamosGuiaDeRemision(String casoDePrueba) throws Throwable {

        try {
            int guiarem = Integer.parseInt(casoDePrueba) - 1;
            driver.findElement(CargaMateriales.TXT_GUIA_REMISION).clear();
            int random = ThreadLocalRandom.current().nextInt(10, 99);
            int random2 = ThreadLocalRandom.current().nextInt(10, 99);
            int random3 = ThreadLocalRandom.current().nextInt(10, 99);
            int random4 = ThreadLocalRandom.current().nextInt(1, 9);
            int random5 = ThreadLocalRandom.current().nextInt(1, 9);
            int random6 = ThreadLocalRandom.current().nextInt(1, 9);
            String numero = "12"+random6+random5+"-"+ random + random2 + random3+random4;
            driver.findElement(CargaMateriales.TXT_GUIA_REMISION).clear();
            driver.findElement(CargaMateriales.TXT_GUIA_REMISION).sendKeys(numero);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Ingresamos guia de remision");
            generateWord.sendText("Ingresamos guia de remision");
            generateWord.addImageToWord(driver);
        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void ingresamosElArchivo() throws Throwable {

        try {
            driver.findElement(CargaMateriales.BTN_ADJUNTAR_ARCHIVOS).click();
            Actions act = new Actions(driver);
            act.moveToElement(driver.findElement(CargaMateriales.LNK_ADJUNTAR_NUEVO_ARCHIVO)).build().perform();
            driver.findElement(CargaMateriales.LNK_ARCHIVO_NUEVO).click();
            Thread.sleep(2000);
            System.out.println("passs");
            Thread.sleep(2000);
            generateWord.sendText("Agregamos nuevo archivo");
            generateWord.addImageToWord(driver);
            driver.switchTo().frame(0);
            driver.findElement(CargaMateriales.BTN_SELECCIONAR_ARCHIVO).click();
            Thread.sleep(1000);
            Robot robot = new Robot();
            String ruta = "F:\\CargaDeMateriales\\AsignacionSeries_3.csv";
            String text = ruta;
            StringSelection stringSelection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, stringSelection);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(4000);
            Screen screen = new Screen();
            screen.wait(CargaMateriales.BTN_ACEPTAR_ARCHIVO);
            Region valBtn = screen.find(CargaMateriales.BTN_ACEPTAR_ARCHIVO).highlight(1,"green");
            screen.click(CargaMateriales.BTN_ACEPTAR_ARCHIVO);


        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void clickEnEjecutarAjusteYAceptarMensaje() throws Exception {
        try {
            Thread.sleep(5000);
            driver.findElement(CargaMateriales.BTN_EJECUTAR_AJUSTE).click();
            ExtentReportUtil.INSTANCE.stepPass(driver, "Ejecutar ajuste");
            generateWord.sendText("Ejecutar ajuste");
            generateWord.addImageToWord(driver);
            Thread.sleep(2000);
            driver.findElement(CargaMateriales.BTN_ACEPTAR_AJUSTE).click();
            Thread.sleep(7000);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Mensaje del sistema");
            generateWord.sendText("Mensaje del sistema");
            generateWord.addImageToWord(driver);
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(CargaMateriales.BTN_ACEPTAR_SISTEMA));
            String text;
            text = driver.findElement(CargaMateriales.TXT_IMAGEN).getText();
            text = text.substring(13);
            if (text.equals("Error en el proceso, verificar el campo de error")){
                ExtentReportUtil.INSTANCE.stepFail(driver, "Error al cargar materiales");
                generateWord.sendText("Error al cargar materiales");
                generateWord.addImageToWord(driver);
            }
            if (text.equals("Ajuste ejecutado con éxito") || text.equals(" Ajuste ejecutado con éxito") ){
                ExtentReportUtil.INSTANCE.stepPass(driver, "Carga de materiales exitoso");
                generateWord.sendText("Carga de materiales exitoso");
                generateWord.addImageToWord(driver);
            }
            driver.findElement(CargaMateriales.BTN_ACEPTAR_SISTEMA).click();
            Thread.sleep(4000);

        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void validarQueLosArchivosHayanCargado() throws Exception {
        try {

            String filas;
            filas = driver.findElement(CargaMateriales.TABLE).getAttribute("displayrows");
            int num = Integer.parseInt(filas);
            for (int  i =0; (i<num); i++){
                String valor = driver.findElement(By.id("me7037f0c_tdrow_[C:10]-c[R:"+i+"]")).getText();
                String material = driver.findElement(By.id("me7037f0c_tdrow_[C:7]-c[R:"+i+"]")).getText();
                System.out.println(material + "  ->  " + valor);

            }
            ExtentReportUtil.INSTANCE.stepPass(driver, "Detalle de carga");
            generateWord.sendText("Detalle de carga");
            generateWord.addImageToWord(driver);
        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
}
