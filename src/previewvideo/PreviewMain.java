/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package previewvideo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author marcom
 */
public class PreviewMain extends javax.swing.JFrame {
    
    public static String ERROR_ROUTE_EMPTY = "Ruta Empty";
    public static String ERROR_FINISH = "Termino Proceso";
    public static String ERROR_TIME_EXCEPTION = "No Se Pudo Calcular El Tiempo";
    public static String ERROR_FAIL_LOAD_PICS = "Error al cargar las imagenes";
    public static String ERROR_FAIL_SAVE_PIC = "Error al guardar la imagen";
    
    public static int tw = 1;
    public static int th = 1;
    
    public boolean canLog = false;
    
    public String ffmpegUrl = "";
    public String mode = "";
    
    int anterior = 0;
    double fpsp = 0;
    
    public PreviewMain() {
        initComponents();
        //this.setLocationRelativeTo(null);
        readConfig();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        file = new javax.swing.JFileChooser();
        jButton1 = new javax.swing.JButton();
        info = new javax.swing.JLabel();
        pathito = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        video = new javax.swing.JTextField();
        progress = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        log = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton1.setText("Run");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/previewvideo/gato.jpg"))); // NOI18N
        info.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                infoMouseClicked(evt);
            }
        });

        jLabel1.setText("Path:");

        jLabel2.setText("Video");

        jScrollPane1.setViewportView(log);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(video)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pathito)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(progress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(info)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pathito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(video, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(progress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Metodos estaticos
    public static int getPrevFiles(){
        return (new File("prev") ).listFiles().length;
    }
    public void readConfig(){
        try{
            File file = new File("config.txt"); 
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String string;
            while ((string = br.readLine()) != null){
                if(string.contains("frames")){
                    mode = string.replace("frames:", "");
                    if(mode.contains("4x4")){
                        PreviewMain.th = 4;
                        PreviewMain.tw = 4;
                    }
                    if(mode.contains("3x3")){
                        PreviewMain.th = 3;
                        PreviewMain.tw = 3;
                    }
                    if(mode.contains("2x2")){
                        PreviewMain.th = 2;
                        PreviewMain.tw = 2;
                    }
                    if(mode.contains("1x1")){
                        PreviewMain.th = 1;
                        PreviewMain.tw = 1;
                    }
                    System.out.println(mode);
                }
                if(string.contains("ffmpeg")){
                    ffmpegUrl = string.replace("ffmpeg:", "");
                    System.out.println(ffmpegUrl);
                }
                System.out.println(string);
            }
        }
        catch(Exception ex){
            System.out.println("Trono papu"+ex.toString());
        }
    } 
    // Llamadas ordenadas
    public String searchVideo(String url){
        File folder =  new File(url);
        File[] all = folder.listFiles();
        int actual = 0;
        this.anterior = video.getText().isEmpty()?0:Integer.parseInt(video.getText());
        
        if(this.anterior > all.length){
            return "finish";
        }
        for (File fileActual : all) {
            String name = fileActual.getName();
            String extension = name.substring(name.length()-3,name.length());
            if (extension.toLowerCase().contains("mp4") && actual >= anterior){
                return fileActual.getAbsolutePath();
            }
            else if(extension.toLowerCase().contains("mp4")){
                actual++;
            }
        }
        return "finish";
    }
    public String getVideoTime(String archivo){
        try{
            String data = "";
            String info_command = ffmpegUrl + " -i \"" + archivo + "\"";
            System.out.println(info_command);
            Process proc = Runtime.getRuntime().exec(info_command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            String s = "";
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
                if (s.contains("Duration")) {
                    data = s;
                    break;
                }
            }
            int init = data.indexOf(':')+2;
            int end = data.indexOf(',');
            System.out.println(data);
            return data.substring(init, end);
        }
        catch(Exception ex){
            return PreviewMain.ERROR_TIME_EXCEPTION;
        }
    }
    private double getTimeSec(String data){
        String horas = data.split(":")[0];
        String minutos = data.split(":")[1];
        String segundos = data.split(":")[2];
        return Double.parseDouble(horas)*3600 + Double.parseDouble(minutos)*60  + Math.floor(Double.parseDouble(segundos));
    }
    public String makePic(String duration, String archivo){
        logger("Making Pic ...");
        ArrayList<Image> pics = new ArrayList();
        try{
            logger("Trying to read Pics");
            for (int i = 1; i <= (PreviewMain.th * PreviewMain.tw); i++) {
                pics.add(ImageIO.read(new File("prev/img"+i+".jpg")));
                logger(i+" of "+(PreviewMain.th * PreviewMain.tw));
            }
        }
        catch(Exception ex){
            logger("Can't Read");
            return PreviewMain.ERROR_FAIL_LOAD_PICS;
        }
        logger("Read Success");
        int width = pics.get(0).getWidth(info) * PreviewMain.tw + 40;
        int height = pics.get(0).getHeight(info) * PreviewMain.th + 40;
        logger("Starting Map: "+width+" x "+height);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        logger("Map Start");
        Graphics2D g2d = bufferedImage.createGraphics();
        logger("Making background");
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, height);
        logger("Background finish");
        int wr = width/PreviewMain.tw;
        int hr = height/PreviewMain.th;
        int index = 0;
        logger("creating map");
        for(int h = 0; h<PreviewMain.th; h++){
            for(int w = 0; w<PreviewMain.tw; w++){
                g2d.drawImage(pics.get(index++), (wr*w)+10, (hr*h)+10, info);
            }
        }
        logger("Map Done");
        BufferedImage neuw = scaleImage(bufferedImage, 1200, 1200, Color.black);
        
        g2d = neuw.createGraphics();
        
        g2d.setColor(Color.BLACK);
        g2d.fillRect(4, 4, 212, 22);
        
        g2d.setColor(Color.white);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g2d.drawString("Duración:" + duration, 10, 20);
        
        g2d.dispose();
        try{
            logger("Save file");
            File f = new File(archivo);
            log.setText("<html>"+log.getText()+"<br>"+f.getAbsolutePath()+"</html>");
            File file = new File(f.getAbsolutePath()+"_preview_.jpg");
            ImageIO.write(neuw, "jpg", file);
            logger("File Save");
        }
        catch(Exception ex){
            logger("Can't Save File");
            return PreviewMain.ERROR_FAIL_SAVE_PIC;
        }
        return "";
    }
    private BufferedImage scaleImage(BufferedImage img, int width, int height, Color background) {
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        if (imgWidth * height < imgHeight * width) {
            width = imgWidth * height / imgHeight;
        } else {
            height = imgHeight * width / imgWidth;
        }
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newImage.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.setBackground(background);
            g.clearRect(0, 0, width, height);
            g.drawImage(img, 0, 0, width, height, null);
        } finally {
            g.dispose();
        }
        return newImage;
    }
    public void deleteAll(){
        for (File listFile : (new File("prev") ).listFiles()) {
            listFile.delete();
        }
        logger("Restart...");
        restart();
    }
    // Metodos adicionales
    public void logger(String text){
        String logData = log.getText();
        logData = logData.replace("<html>", "");
        logData = logData.replace("</html>", "");
        log.setText("<html>"+logData + text + "<br/></html>");
        if(canLog){
            try{ Thread.sleep(1000); } catch(Exception ex){ }
        }
    }
    public boolean startProcess(String archivo){
        try {
            String preview_command = ffmpegUrl + " -hwaccel nvdec -i \""+archivo+ "\" -vf fps=1/"+Math.floor(fpsp)+ " \"prev/img%d.jpg\"";
            System.out.println(preview_command);
            Process proc = Runtime.getRuntime().exec(preview_command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Something happen"+ex);
            return false;
        }
        return true;
    }
    public void stopProcess(){
        try {
            String preview_command = "taskkill -im -f ffmpeg.exe";
            Process proc = Runtime.getRuntime().exec(preview_command);
        } catch (Exception ex) {
            
        }
    }
    public boolean isProcessOn(){
        boolean contains = false;
        try{
            Process proc = Runtime.getRuntime().exec("tasklist");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String s = "";
            while ((s = stdInput.readLine()) != null) {
                if(s.contains("ffmpeg")){
                    return true;
                }
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "398:"+ex);
        }
        return contains;
    }
    public void restart(){
        try{
            Runtime.getRuntime().exec("java -jar PreviewVideo.jar \"" + pathito.getText() +"\" \""+video.getText());
            System.exit(0);
        }catch(Exception ex){
            
        }
    }
    public String makeFunction(){
        // Adquirir ruta
        String ruta = pathito.getText();
        if(ruta.isEmpty()) return PreviewMain.ERROR_ROUTE_EMPTY;
        logger(ruta);
        // adquirir archivo
        String archivo = searchVideo(ruta);
        if(archivo.equals("finish")) return PreviewMain.ERROR_FINISH;
        String []potato = archivo.split("\\\\");
        logger(potato[potato.length-1]);
        // adquirir tiempo
        String time = getVideoTime(archivo);
        if(time.equals(PreviewMain.ERROR_TIME_EXCEPTION)) return PreviewMain.ERROR_TIME_EXCEPTION;
        double duration_sec = getTimeSec(time);
        this.fpsp = duration_sec / (PreviewMain.tw * PreviewMain.th);
        logger("Can Create Picture?");
        // ¿Se puede hacer imagen?
        if(PreviewMain.getPrevFiles() >= (PreviewMain.tw * PreviewMain.th) ){
            logger("Making Picture");
            String pic = makePic(time, archivo);
            logger("Done");
            if(!pic.isEmpty()) return pic;
            this.anterior++;
            this.video.setText(this.anterior+"");
            deleteAll();
        }
        else if(!isProcessOn()){
            logger("Start Process");
            startProcess(archivo);
            logger("Resstart");
            restart();
        }
        return "";
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String ruta = pathito.getText();
        if(ruta.isEmpty()) return ;
        startProcess(ruta);
        restart();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void infoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoMouseClicked
        canLog = !canLog;
    }//GEN-LAST:event_infoMouseClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PreviewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PreviewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PreviewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PreviewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        String data = "";
        for (String arg : args) {
            data += arg;
        }
        final PreviewMain frame = new PreviewMain();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame.setVisible(true);
                if(args.length >=2){
                    frame.pathito.setText(args[0]);
                    frame.video.setText(args[1]);
                }
                if(args.length >=3){
                    frame.log.setText("Current File: "+args[2]);
                }
            }
        });
        TofThread t = new TofThread();
        t.setFrame(frame);
        new Thread(t).start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser file;
    private javax.swing.JLabel info;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel log;
    public javax.swing.JTextField pathito;
    public javax.swing.JProgressBar progress;
    public javax.swing.JTextField video;
    // End of variables declaration//GEN-END:variables
}


class TofThread implements Runnable{
    private PreviewMain p;
    private float time = 0;
    public void setFrame(PreviewMain p){
        this.p = p;
    }
    public void run(){
        p.progress.setMaximum(PreviewMain.tw * PreviewMain.th);
        while(true){
            p.progress.setValue(PreviewMain.getPrevFiles());
            if(!p.pathito.getText().isEmpty() && !p.isProcessOn()){
                try{ Thread.sleep(1000); }
                catch(Exception ex){ p.logger("Sleep Death"); }
                System.out.println(p.makeFunction());
            }
            else{
                try{ Thread.sleep(500); } catch(Exception ex){ p.logger("Sleep Death"); }
            }
        }
    }
}