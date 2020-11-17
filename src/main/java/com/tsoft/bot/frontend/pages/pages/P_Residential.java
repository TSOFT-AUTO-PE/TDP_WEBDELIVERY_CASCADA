package com.tsoft.bot.frontend.pages.pages;

import com.tsoft.bot.frontend.Base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.ExcelResidencial;
import com.tsoft.bot.frontend.pages.objects.O_CargaMateriales;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.ExtentReportUtil;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class P_Residential extends BaseClass {

    public WebDriver driver;
    static GenerateWord generateWord = new GenerateWord();
    public P_Residential( WebDriver driver) {
        super(driver);
        this.driver = Hook.getDriver();
    }
    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(ExcelResidencial.EXCEL_WEB, ExcelResidencial.ORDEN);
    }
        public void ingresoALaUrlDeWEBDELIVERY(String casoDePrueba) throws Throwable {
            try {
                int LoginWD = Integer.parseInt(casoDePrueba) - 1;
                String url = getData().get(LoginWD).get(ExcelResidencial.COLUMNA_URL);
                driver.get(url);
                stepPass(driver,"Se cargó correctamente la página");
                generateWord.sendText("Carga correcta de la página");
                generateWord.addImageToWord(driver);
                println("[LOG] Se cargó correctamente la página");
                generateWord.sendBreak();
            }catch (Exception e){
                ExcelReader.writeCellValue(ExcelResidencial.EXCEL_WEB, ExcelResidencial.ORDEN, 1, 19, "FAIL");
                ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
                generateWord.sendText("Tiempo de espera ha excedido");
                generateWord.addImageToWord(driver);
            }
        }

        public void ingresoElUsuarioDeWEBDELIVERY(String casoDePrueba) throws Throwable {

            try {
                int user = Integer.parseInt(casoDePrueba) - 1;
                String usuario = getData().get(user).get(ExcelResidencial.COLUMNA_USUARIO);
                wait(driver, O_CargaMateriales.TXT_USER,60);
                if (isDisplayed(driver, O_CargaMateriales.TXT_USER)){
                    clear(driver, O_CargaMateriales.TXT_USER);
                    sendKeys(driver, O_CargaMateriales.TXT_USER,usuario);
                }
                stepPass(driver,"Ingresamos el usuario");
                generateWord.sendText("Ingresamos el usuario");
                generateWord.addImageToWord(driver);
                println("[LOG] Ingresamos usuario");

            }catch (Exception e){
                ExcelReader.writeCellValue(ExcelResidencial.EXCEL_WEB, ExcelResidencial.ORDEN, 1, 19, "FAIL");
                ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
                generateWord.sendText("Tiempo de espera ha excedido");
                generateWord.addImageToWord(driver);
            }
        }
        public void laContraseñaDeWEBDELIVERY(String casoDePrueba) throws Throwable {
            try {
                int PASS = Integer.parseInt(casoDePrueba) - 1;
                wait(driver, O_CargaMateriales.TXT_PASSWORD,60);
                clear(driver, O_CargaMateriales.TXT_PASSWORD);
                String contra = getData().get(PASS).get(ExcelResidencial.COLUMNA_CONTRASENIA);
                sendKeys(driver, O_CargaMateriales.TXT_PASSWORD,contra);
                stepPass(driver,"Ingresamos la contraseña");
                generateWord.sendText("Ingresamos la contraseña");
                generateWord.addImageToWord(driver);
                println("[LOG] Ingresamos contraseña");
            }catch (Exception e){
                ExcelReader.writeCellValue(ExcelResidencial.EXCEL_WEB, ExcelResidencial.ORDEN, 1, 19, "FAIL");
                ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
                generateWord.sendText("Tiempo de espera ha excedido");
                generateWord.addImageToWord(driver);
            }
        }
        public void seDaClicEnElBotonLoginDeWEBDELIVERYIngresandoCorrectamente() throws Throwable {
            try {
                click(driver, O_CargaMateriales.BTN_LOGIN);
                sleep(2000);
                wait(driver,O_CargaMateriales.LNK_CREAR_PEDIDO,60);
                stepPass(driver,"Se ingresa correctamente a la pagina");
                generateWord.sendText("Se ingresa correctamente a la pagina");
                generateWord.addImageToWord(driver);
                println("[LOG] Logueo exitoso");
            }catch (Exception e){
                ExcelReader.writeCellValue(ExcelResidencial.EXCEL_WEB, ExcelResidencial.ORDEN, 1, 19, "FAIL");
                ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
                generateWord.sendText("Tiempo de espera ha excedido");
                generateWord.addImageToWord(driver);
            }
        }
    public void seDaClickEnElBotonIRAEnWEBDELIVERY(String arg0) throws Throwable {
        try {
            click(driver, O_CargaMateriales.LST_IR_A);
            ExtentReportUtil.INSTANCE.stepPass(driver, "IR A lista de pedidos");
        }catch (Exception e){
            ExcelReader.writeCellValue(ExcelResidencial.EXCEL_WEB, ExcelResidencial.ORDEN, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void seleccionarAjusteDeInventario() throws Throwable {
        try {
            Actions act = new Actions(driver);
            act.moveToElement(driver.findElement(O_CargaMateriales.LNK_GESTION_PEDIDOS)).build().perform();
            Actions act2 = new Actions(driver);
            act2.moveToElement(driver.findElement(O_CargaMateriales.LNK_GESTION_INVENTARIOS)).build().perform();
            click(driver,O_CargaMateriales.LNK_AJUSTE_INVENTARIO);
            sleep(2000);
            stepPass(driver,"Ajuste de inventario");
            generateWord.sendText("Ajuste de inventario");
            generateWord.addImageToWord(driver);
            println("[LOG] Ajuste de inventario");
        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void clickEnElBotonNuevoRegistro() throws Throwable {
        try {
            click(driver,O_CargaMateriales.BTN_NUEVO_REGISTRO);
            sleep(2000);
            stepPass(driver,"Seleccionamos nuevo registro");
            generateWord.sendText("Seleccionamos nuevo registro");
            generateWord.addImageToWord(driver);
            println("[LOG] Seleccionamos nuevo registro");
        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void seleccionamosElTipoABASTECIMIENTO(String arg0) throws Throwable {
        try {
            click(driver,O_CargaMateriales.BTN_TIPO);
            sleep(1000);
            click(driver,O_CargaMateriales.LNK_ABASTECIMIENTO);
            sleep(2000);
            String estado = driver.findElement(O_CargaMateriales.TXT_TIPO).getAttribute("value");
            if (estado.equals("ABASTECIMIENTO")){
                stepPass(driver,"Seleccionamos tipo: ABASTECIMIENTO");
                generateWord.sendText("Seleccionamos tipo: ABASTECIMIENTO");
                generateWord.addImageToWord(driver);
                println("[LOG] Seleccionamos tipo: ABASTECIMIENTO");
            }else {
                stepFail(driver,"No seleccionó ABASTECIMIENTO");
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
            clear(driver,O_CargaMateriales.TXT_COMENTARIO);
            sendKeys(driver,O_CargaMateriales.TXT_COMENTARIO,"PRUEBAS-QA");
            stepPass(driver,"Ingresamos comentario");
            generateWord.sendText("Ingresamos comentario");
            generateWord.addImageToWord(driver);
            println("[LOG] Ingresamos comentario: PRUEBAS-QA");
        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void ingresamosGuiaDeRemision(String casoDePrueba) throws Throwable {

        try {
            clear(driver,O_CargaMateriales.TXT_GUIA_REMISION);
            int random = ThreadLocalRandom.current().nextInt(10, 99);
            int random2 = ThreadLocalRandom.current().nextInt(10, 99);
            int random3 = ThreadLocalRandom.current().nextInt(10, 99);
            int random4 = ThreadLocalRandom.current().nextInt(1, 9);
            int random5 = ThreadLocalRandom.current().nextInt(1, 9);
            int random6 = ThreadLocalRandom.current().nextInt(1, 9);
            String numero = "12"+random6+random5+"-"+ random + random2 + random3+random4;
            sendKeys(driver,O_CargaMateriales.TXT_GUIA_REMISION,numero);
            stepPass(driver,"Ingresamos guia de remision");
            generateWord.sendText("Ingresamos guia de remision");
            generateWord.addImageToWord(driver);
            println("[LOG] Guia de remision ingresada: "+numero);
        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void ingresamosElArchivo() throws Throwable {
        try {
            click(driver,O_CargaMateriales.BTN_ADJUNTAR_ARCHIVOS);
            Actions act = new Actions(driver);
            act.moveToElement(driver.findElement(O_CargaMateriales.LNK_ADJUNTAR_NUEVO_ARCHIVO)).build().perform();
            click(driver,O_CargaMateriales.LNK_ARCHIVO_NUEVO);
            sleep(2000);
            stepPass(driver,"Ingresamos nuevo archivo");
            generateWord.sendText("Ingresamos nuevo archivo");
            generateWord.addImageToWord(driver);
            println("[LOG] Cargando archivo CSV");
            driver.switchTo().frame(0);
            driver.findElement(O_CargaMateriales.BTN_SELECCIONAR_ARCHIVO).click();
            Thread.sleep(1000);
            Robot robot = new Robot();
            String ruta = "D:\\ASIGNACIONES\\AsignacionSeries_3.csv";
            String text = ruta;
            StringSelection stringSelection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, stringSelection);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            sleep(2000);
            robot.keyPress(KeyEvent.VK_ENTER);
            sleep(4000);
            Screen screen = new Screen();
            screen.wait(O_CargaMateriales.BTN_ACEPTAR_ARCHIVO);
            Region valBtn = screen.find(O_CargaMateriales.BTN_ACEPTAR_ARCHIVO).highlight(1,"green");
            screen.click(O_CargaMateriales.BTN_ACEPTAR_ARCHIVO);
            println("[LOG] Archivo CSV cargado");
        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void clickEnEjecutarAjusteYAceptarMensaje() throws Throwable {
        try {
            sleep(5000);
            click(driver,O_CargaMateriales.BTN_EJECUTAR_AJUSTE);
            stepPass(driver,"Ejecutar ajuste");
            generateWord.sendText("Ejecutar ajuste");
            generateWord.addImageToWord(driver);
            println("[LOG] Ejecutamos ajuste");
            sleep(2000);
            click(driver,O_CargaMateriales.BTN_ACEPTAR_AJUSTE);
            sleep(7000);
            stepPass(driver,"Mensaje del sistema");
            generateWord.sendText("Mensaje del sistema");
            generateWord.addImageToWord(driver);
            wait(driver,O_CargaMateriales.BTN_ACEPTAR_SISTEMA,60);
            String text;
            text = driver.findElement(O_CargaMateriales.TXT_IMAGEN).getText();
            text = text.substring(13);
            if (text.equals("Error en el proceso, verificar el campo de error")){
               stepFail(driver,"Error al cargar materiales");
                generateWord.sendText("Error al cargar materiales");
                generateWord.addImageToWord(driver);
                println("[LOG] Error en la carga de materiales");
            }
            if (text.equals("Ajuste ejecutado con éxito") || text.equals(" Ajuste ejecutado con éxito") ){
                stepPass(driver,"Carga de materiales exitoso");
                generateWord.sendText("Carga de materiales exitoso");
                generateWord.addImageToWord(driver);
                println("[LOG] Materiales cargados correctamente");
            }
            click(driver,O_CargaMateriales.BTN_ACEPTAR_SISTEMA);
            sleep(4000);

        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void validarQueLosArchivosHayanCargado() throws Throwable {
        try {

            String filas;
            filas = driver.findElement(O_CargaMateriales.TABLE).getAttribute("displayrows");
            println("[LOG] ------ Detalle ------");
            int num = Integer.parseInt(filas);
            for (int  i =0; (i<num); i++){
                String valor = driver.findElement(By.id("me7037f0c_tdrow_[C:10]-c[R:"+i+"]")).getText();
                String material = driver.findElement(By.id("me7037f0c_tdrow_[C:7]-c[R:"+i+"]")).getText();
                println(material + "  ->  " + valor);
            }
            stepPass(driver,"Detalle de carga");
            generateWord.sendText("Detalle de carga");
            generateWord.addImageToWord(driver);
        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
}
