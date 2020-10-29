package com.tsoft.bot.frontend.pages.pages;

import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.Corporativo;
import com.tsoft.bot.frontend.pages.objects.ExcelWebDelivery;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.ExtentReportUtil;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.ImagePath;

import java.util.HashMap;
import java.util.List;

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
}
