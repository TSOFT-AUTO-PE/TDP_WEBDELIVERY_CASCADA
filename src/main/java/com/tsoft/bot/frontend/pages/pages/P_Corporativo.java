package com.tsoft.bot.frontend.pages.pages;

import com.tsoft.bot.frontend.Base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.ExcelCorporativo;
import com.tsoft.bot.frontend.pages.objects.O_Corporate;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.ExtentReportUtil;
import com.tsoft.bot.frontend.utility.GenerateWord;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;

public class P_Corporativo extends BaseClass {
    String NUM_ENVIO;
    String Fecha;
    String reserva;
    String pedido;
    String flujo;
    String tipo;
    static GenerateWord generateWord = new GenerateWord();
    public WebDriver driver;
    public P_Corporativo( WebDriver driver) {
        super(driver);
        this.driver = Hook.getDriver();
    }
    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(ExcelCorporativo.EXCEL_WEB, ExcelCorporativo.ORDEN);
    }

    public void ingresamosALAURLWEBDELIVERY(String casoDePrueba) throws Throwable {
        try {
            int LoginWD = Integer.parseInt(casoDePrueba) - 1;
            String url = getData().get(LoginWD).get(ExcelCorporativo.COLUMNA_URL);
            driver.get(url);
            stepPass(driver,"Se cargó correctamente la página");
            generateWord.sendText("Se cargó correctamente la página");
            generateWord.addImageToWord(driver);
            println("[LOG] Se cargó correctamente la página");
            generateWord.sendBreak();
        }catch (Exception e){
            ExcelReader.writeCellValue(ExcelCorporativo.EXCEL_WEB, ExcelCorporativo.ORDEN, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }

    public void ingresamosUSUARIOWEBDELIVERY(String casoDePrueba) throws Throwable {
        try {
            int user = Integer.parseInt(casoDePrueba) - 1;
            String usuario = getData().get(user).get(ExcelCorporativo.COLUMNA_USUARIO);
            wait(driver, O_Corporate.TXT_USER,60);
            if (driver.findElement(O_Corporate.TXT_USER).isDisplayed()){
                clear(driver,O_Corporate.TXT_USER);
                sendKeys(driver,O_Corporate.TXT_USER,usuario);
            }
            stepPass(driver,"Ingresamos el usuario");
            generateWord.sendText("Ingresamos el usuario");
            generateWord.addImageToWord(driver);
            println("[LOG] Ingreso correcto del usuario");
            generateWord.sendBreak();
        }catch (Exception e){
            ExcelReader.writeCellValue(ExcelCorporativo.EXCEL_WEB, ExcelCorporativo.ORDEN, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void ingresamosCONTRASEÑAWEBDELIVERY(String casoDePrueba) throws Throwable {
        try {
            int PASS = Integer.parseInt(casoDePrueba) - 1;
            wait(driver,O_Corporate.TXT_PASSWORD,60);
            clear(driver,O_Corporate.TXT_PASSWORD);
            String contrasenia = getData().get(PASS).get(ExcelCorporativo.COLUMNA_CONTRASENIA);
            sendKeys(driver,O_Corporate.TXT_PASSWORD,contrasenia);
            stepPass(driver,"Ingresamos contraseña");
            generateWord.sendText("Ingresamos contraseña");
            generateWord.addImageToWord(driver);
            println("[LOG] Ingreso correcto de la contraseña");
            generateWord.sendBreak();
        }catch (Exception e){
            ExcelReader.writeCellValue(ExcelCorporativo.EXCEL_WEB, ExcelCorporativo.ORDEN, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void clickBOTONLOGINWEBDELIVERYYSEINGRESACORRECTAMENTE() throws Throwable {
        try {
            click(driver,O_Corporate.BTN_LOGIN);
            sleep(2000);
            wait(driver,O_Corporate.LST_IR_A,60);
            stepPass(driver,"Se ingresa correctamente a la pagina");
            generateWord.sendText("Se ingresa correctamente a la pagina");
            generateWord.addImageToWord(driver);
            println("[LOG] Logueo exitoso");
            generateWord.sendBreak();
        }catch (Exception e){
            ExcelReader.writeCellValue(ExcelCorporativo.EXCEL_WEB, ExcelCorporativo.ORDEN, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void seleccionamosAsignaciónDeSeriesCorporativo() throws Throwable {
        click(driver,O_Corporate.LST_IR_A);
        sleep(2000);
        MoveToElement(driver,O_Corporate.LNK_GEST_PED_CORP);
        sleep(1000);
        MoveToElement(driver,O_Corporate.LNK_PREP_PEDIDO);
        click(driver,O_Corporate.LNK_ASIG_SERIES);
        wait(driver,O_Corporate.TXT_ID_RESERVA,60);
        stepPass(driver,"Asignación de series");
        generateWord.sendText("Asignación de series");
        generateWord.addImageToWord(driver);
        println("[LOG] Asignación de series");
        generateWord.sendBreak();
    }
    public void buscamosElIdDeReservaCorporativo(String casoDePrueba) throws Throwable {
        try {
            int pedido1 = Integer.parseInt(casoDePrueba) - 1;
            String user1 = getData().get(pedido1).get(ExcelCorporativo.IDRESERVA);
            clear(driver,O_Corporate.TXT_ID_RESERVA);
            sendKeys(driver,O_Corporate.TXT_ID_RESERVA,user1);
            println("[LOG] Id de reserva ingresado");
            sendKeysRobot(driver,O_Corporate.TXT_ID_RESERVA, Keys.ENTER);
            String f = driver.findElement(O_Corporate.TXT_ORDEN).getText();
            while (!f.equals(user1)) {
                sleep(1000);
                String g;
                g = driver.findElement(O_Corporate.TXT_VACIO).getText();
                if (g.equals("0 - 0 de 0")){
                    stepFail(driver,"ID Reserva no encontrado");
                    generateWord.sendText("ID Reserva no encontrado");
                    generateWord.addImageToWord(driver);
                    println("[LOG] Id de reserva no encontrado");
                    driver.quit();
                }else{
                    f = driver.findElement(O_Corporate.TXT_ORDEN).getText();
                }
            }
            stepPass(driver,"ID Reserva encontrado");
            generateWord.sendText("ID Reserva encontrado");
            generateWord.addImageToWord(driver);
            println("[LOG] Id de reserva encontrado");

        } catch (Exception e) {
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void ingresamosMaterialesIMEIYSIMCARD(String casoDePrueba) throws Throwable {
        try {
            int CantFilas = Integer.parseInt(casoDePrueba) - 1;
            String filas;
            filas = driver.findElement(O_Corporate.TABLE2).getAttribute("displayrows");
            int num = Integer.parseInt(filas);
            int s = CantFilas;
            int f = CantFilas;
            for (int  i =0; (i<num); i++){
                String TipoMaterial = driver.findElement(By.id("md6723283_tdrow_[C:6]-c[R:"+i+"]")).getText();
                if (TipoMaterial.equals("IMEI")){
                    String imei = getData().get(s).get(ExcelCorporativo.NUMERO_IMEI);
                    driver.findElement(By.id("md6723283_tdrow_[C:9]_txt-tb[R:"+i+"]")).clear();
                    driver.findElement(By.id("md6723283_tdrow_[C:9]_txt-tb[R:"+i+"]")).sendKeys(imei);
                    sleep(1000);
                    s++;
                }
                if (TipoMaterial.equals("ICCID")){
                    String sim = getData().get(f).get(ExcelCorporativo.NUMERO_SIMCARD);
                    driver.findElement(By.id("md6723283_tdrow_[C:9]_txt-tb[R:"+i+"]")).clear();
                    driver.findElement(By.id("md6723283_tdrow_[C:9]_txt-tb[R:"+i+"]")).sendKeys(sim);
                    sleep(1000);
                    f++;
                }
                if (i==num-1){
                    stepPass(driver,"Materiales ingresados");
                    generateWord.sendText("Materiales ingresados");
                    generateWord.addImageToWord(driver);
                    println("[LOG] Materiales ingresados");
                    break;
                }
            }
            sleep(2000);

/*
            int CantFilas = Integer.parseInt(casoDePrueba) - 1;
            String Cant_Filas = getData().get(CantFilas).get(CANT_FILAS);
            int Pedido = Integer.parseInt(casoDePrueba) - 1;
            String Pedidos = getData().get(Pedido).get(TIPO_PEDIDO);
            if (Cant_Filas.equals("6")&& Pedidos.equals("EQUIPO+SIM")){
                int imei1 = Integer.parseInt(casoDePrueba) - 1;
                String imei_1 = getData().get(imei1).get(NUMERO_IMEI);
                driver.findElement(TXT_MATERIAL_1).clear();
                driver.findElement(TXT_MATERIAL_1).sendKeys(imei_1);
                int sim1 = Integer.parseInt(casoDePrueba) - 1;
                String sim_1 = getData().get(sim1).get(NUMERO_SIMCARD);
                driver.findElement(TXT_MATERIAL_2).clear();
                driver.findElement(TXT_MATERIAL_2).sendKeys(sim_1);
                String imei_2 = getData().get(1).get(NUMERO_IMEI);
                driver.findElement(TXT_MATERIAL_3).clear();
                driver.findElement(TXT_MATERIAL_3).sendKeys(imei_2);
                String sim_2 = getData().get(1).get(NUMERO_SIMCARD);
                driver.findElement(TXT_MATERIAL_4).clear();
                driver.findElement(TXT_MATERIAL_4).sendKeys(sim_2);
                String imei_3 = getData().get(2).get(NUMERO_IMEI);
                driver.findElement(TXT_MATERIAL_5).clear();
                driver.findElement(TXT_MATERIAL_5).sendKeys(imei_3);
                String sim_3 = getData().get(2).get(NUMERO_SIMCARD);
                driver.findElement(TXT_MATERIAL_6).clear();
                driver.findElement(TXT_MATERIAL_6).sendKeys(sim_3);
                String imei_4 = getData().get(3).get(NUMERO_IMEI);
                driver.findElement(TXT_MATERIAL_7).clear();
                driver.findElement(TXT_MATERIAL_7).sendKeys(imei_4);
                String sim_4 = getData().get(3).get(NUMERO_SIMCARD);
                driver.findElement(TXT_MATERIAL_8).clear();
                driver.findElement(TXT_MATERIAL_8).sendKeys(sim_4);
                String imei_5 = getData().get(4).get(NUMERO_IMEI);
                driver.findElement(TXT_MATERIAL_9).clear();
                driver.findElement(TXT_MATERIAL_9).sendKeys(imei_5);
                String sim_5 = getData().get(4).get(NUMERO_SIMCARD);
                driver.findElement(TXT_MATERIAL_10).clear();
                driver.findElement(TXT_MATERIAL_10).sendKeys(sim_5);
                String imei_6 = getData().get(5).get(NUMERO_IMEI);
                driver.findElement(TXT_MATERIAL_11).clear();
                driver.findElement(TXT_MATERIAL_11).sendKeys(imei_6);
                String sim_6 = getData().get(5).get(NUMERO_SIMCARD);
                driver.findElement(TXT_MATERIAL_12).clear();
                driver.findElement(TXT_MATERIAL_12).sendKeys(sim_6);
            }
            if (Cant_Filas.equals("1")&& Pedidos.equals("EQUIPO+SIM")) {
                int imei1 = Integer.parseInt(casoDePrueba) - 1;
                String imei_1 = getData().get(imei1).get(NUMERO_IMEI);
                driver.findElement(TXT_MATERIAL_1).clear();
                driver.findElement(TXT_MATERIAL_1).sendKeys(imei_1);
                int sim1 = Integer.parseInt(casoDePrueba) - 1;
                String sim_1 = getData().get(sim1).get(NUMERO_SIMCARD);
                driver.findElement(TXT_MATERIAL_2).clear();
                driver.findElement(TXT_MATERIAL_2).sendKeys(sim_1);
            }
            if  (Cant_Filas.equals("1")&& Pedidos.equals("SOLO SIM")) {
                int sim1 = Integer.parseInt(casoDePrueba) - 1;
                String sim_1 = getData().get(sim1).get(NUMERO_SIMCARD);
                driver.findElement(TXT_MATERIAL_1).clear();
                driver.findElement(TXT_MATERIAL_1).sendKeys(sim_1);
            }
*/
        }catch (Exception e){
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void validamosSeriesCorporativo() throws Throwable {
        try {
            click(driver,O_Corporate.BTN_VALIDAR_SERIE);
            println("[LOG] Validamos materiales");
            wait(driver,O_Corporate.BTN_SI,60);
            stepPass(driver,"Mensaje: Validar Serie");
            generateWord.sendText("Mensaje: Validar Serie");
            generateWord.addImageToWord(driver);
            click(driver,O_Corporate.BTN_SI);
            wait(driver,O_Corporate.BTN_ACEPTAR_MENS_SIST,60);
            stepPass(driver,"Mensaje de Validación");
            generateWord.sendText("Mensaje de Validación");
            generateWord.addImageToWord(driver);
            click(driver,O_Corporate.BTN_ACEPTAR_MENS_SIST);
            sleep(2000);
        } catch (Exception e) {
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void verificarElEstadoDeAsignaciónDeSerieCorporativos(String casoDePrueba) throws Throwable {
        try {
            String filas;
            int error = 0;
            int bien = 0;
            filas = driver.findElement(O_Corporate.TABLE2).getAttribute("displayrows");
            int num = Integer.parseInt(filas);
            for(int  i =0; (i<num); i++){
                String estado = driver.findElement(By.id("md6723283_tdrow_[C:10]-c[R:"+i+"]")).getText();
                if (estado.equals("ERROR")){
                    error++;
                    String valor = driver.findElement(By.id("md6723283_tdrow_[C:9]_txt-tb[R:"+i+"]")).getAttribute("value");
                    println(valor +" --> " + estado);
                }
                if (estado.equals("VALIDADO")){
                    bien++;
                    String valor = driver.findElement(By.id("md6723283_tdrow_[C:9]_txt-tb[R:"+i+"]")).getAttribute("value");
                    println(valor +" --> " + estado);
                }
                if (i == num-1){
                    if (error>0){
                        println("Se obtuvo un total de: " +error+ " materiales con error");
                        println("Se obtuvo un total de: " +bien+ " materiales validados");
                        stepFail(driver,"Materiales no validados");
                        generateWord.sendText("Materiales no validados");
                        println("[LOG] Materiales no validados");
                        generateWord.addImageToWord(driver);
                        driver.quit();
                        break;
                    }else{
                        println("Se obtuvo un total de: " +bien+ " materiales validados");
                        println("[LOG] Materiales validados");
                        ExtentReportUtil.INSTANCE.stepFail(driver, "Materiales validados");
                        generateWord.sendText("Materiales validados");
                        generateWord.addImageToWord(driver);
                        break;
                    }
                }
            }
            /*
            int CantFilas = Integer.parseInt(casoDePrueba) - 1;
            String Cant_Filas = getData().get(CantFilas).get(CANT_FILAS);
            int Pedido = Integer.parseInt(casoDePrueba) - 1;
            String Pedidos = getData().get(Pedido).get(TIPO_PEDIDO);
            if (Cant_Filas.equals("6")&& Pedidos.equals("EQUIPO+SIM")){
                String Estado1 = driver.findElement(LBL_ESTADO_VAL_SERIE1).getText();
                String Estado2 = driver.findElement(LBL_ESTADO_VAL_SERIE2).getText();
                String Estado3 = driver.findElement(LBL_ESTADO_VAL_SERIE3).getText();
                String Estado4 = driver.findElement(LBL_ESTADO_VAL_SERIE4).getText();
                String Estado5 = driver.findElement(LBL_ESTADO_VAL_SERIE5).getText();
                String Estado6 = driver.findElement(LBL_ESTADO_VAL_SERIE6).getText();
                String Estado7 = driver.findElement(LBL_ESTADO_VAL_SERIE7).getText();
                String Estado8 = driver.findElement(LBL_ESTADO_VAL_SERIE8).getText();
                String Estado9 = driver.findElement(LBL_ESTADO_VAL_SERIE9).getText();
                String Estado10 = driver.findElement(LBL_ESTADO_VAL_SERIE10).getText();
                String Estado11 = driver.findElement(LBL_ESTADO_VAL_SERIE11).getText();
                String Estado12 = driver.findElement(LBL_ESTADO_VAL_SERIE12).getText();
                if (Estado1.equals("VALIDADO") && Estado2.equals("VALIDADO") && Estado3.equals("VALIDADO") && Estado4.equals("VALIDADO")&& Estado5.equals("VALIDADO") && Estado6.equals("VALIDADO") && Estado7.equals("VALIDADO") && Estado8.equals("VALIDADO") && Estado9.equals("VALIDADO") && Estado10.equals("VALIDADO") && Estado11.equals("VALIDADO") && Estado12.equals("VALIDADO")){
                    ExtentReportUtil.INSTANCE.stepPass(driver, "Estado de Validación de series: VALIDADO");
                    generateWord.sendText("Estado de Validación de series: VALIDADO");
                    generateWord.addImageToWord(driver);
                }else {
                    ExtentReportUtil.INSTANCE.stepFail(driver, "IMEI Y SIMCARD no validados");
                    generateWord.sendText("IMEI Y SIMCARD no validados");
                    generateWord.addImageToWord(driver);
                }
            }
            if (Cant_Filas.equals("1")&& Pedidos.equals("EQUIPO+SIM")){
                String Estado1 = driver.findElement(LBL_ESTADO_VAL_SERIE1).getText();
                String Estado2 = driver.findElement(LBL_ESTADO_VAL_SERIE2).getText();
                if (Estado1.equals("VALIDADO") && Estado2.equals("VALIDADO")){
                    ExtentReportUtil.INSTANCE.stepPass(driver, "Estado de Validación de series: VALIDADO");
                    generateWord.sendText("Estado de Validación de series: VALIDADO");
                    generateWord.addImageToWord(driver);
                }else {

                        ExtentReportUtil.INSTANCE.stepFail(driver, "IMEI Y SIMCARD no validados");
                        generateWord.sendText("IMEI Y SIMCARD no validados");
                        generateWord.addImageToWord(driver);
                        driver.quit();
                }
            }
            if (Cant_Filas.equals("1")&& Pedidos.equals("SOLO SIM")){
                String Estado1 = driver.findElement(LBL_ESTADO_VAL_SERIE1).getText();
                if (Estado1.equals("VALIDADO")){
                    ExtentReportUtil.INSTANCE.stepPass(driver, "Estado de Validación de series: VALIDADO" );
                    generateWord.sendText("Estado de Validación de series: VALIDADO");
                    generateWord.addImageToWord(driver);
                }else {
                    ExtentReportUtil.INSTANCE.stepFail(driver, "Materiales no validados");
                    generateWord.sendText("Materiales no validados");
                    generateWord.addImageToWord(driver);
                }
            }*/

        } catch (Exception e) {
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }
    public void seleccionamosImpresionDeDocumentosCorporativo() throws Exception {
        driver.findElement(LST_IR_A).click();
        Actions act1 = new Actions(driver);
        act1.moveToElement(driver.findElement(LNK_GEST_PED_CORP)).build().perform();
        Actions act2 = new Actions(driver);
        act2.moveToElement(driver.findElement(LNK_PREP_PEDIDO)).build().perform();
        driver.findElement(LNK_IMPR_DOC).click();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(TXT_ID_RESERVA2));
        ExtentReportUtil.INSTANCE.stepPass(driver, "Impresión de documentos");
        generateWord.sendText("Impresión de documentos");
        generateWord.addImageToWord(driver);
    }
}
