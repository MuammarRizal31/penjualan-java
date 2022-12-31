/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author dc
 */
public class jual extends javax.swing.JInternalFrame {
Connection conn;
    Statement st;
    ResultSet rs;
    String tgl;
    int old, dec, now;
    /**
     * Creates new form jual
     */
    public jual() {
        initComponents();
         ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
    
        
    otomatis();
    stockTF.hide();
    siapIsi(false);
    tgl();
        tombolNormal();
        tampil();
       
       
    }
public String Kodeba, Namaba, Hargaju, stock;

    public String Kodebar() {
        return Kodeba;
    }

    ;
    public String Namabar() {
        return Namaba;
    }

    ;
    public String Hargaju() {
        return Hargaju;
    }

    ;
    public String stock() {
        return stock;
    }
    public String Kodesu,Namasu;

    public String Kodesup() {
        return Kodesu;
    }

    ;
    
    public String Namasup() {
        return Namasu;
    }

    
    private void bersih(){
        noTF.setText("");
        
        tanggalTF.setDate(null);
        supTF.setText("");
        kodeTF.setText("");
        namaTF.setText("");
        jumlahTF.setText("");
        hjualTF.setText("0");
        thargaTF.setText("0");
        bayarTF.setText("0");
        kembaliTF.setText("0");
        
    }

    private void siapIsi(boolean a){
       noTF.setEnabled(a);
        tanggalTF.setEnabled(a);
        supTF.setEnabled(a);
        kodeTF.setEnabled(a);
        namaTF.setEnabled(a);
        jumlahTF.setEnabled(a);
        hjualTF.setEnabled(a);
        thargaTF.setEnabled(a);
         bayarTF.setEnabled(a);
        kembaliTF.setEnabled(a);
        
        
    }
    
    private void tombolNormal(){
        tambahBT.setEnabled(true);
        simpanBT.setEnabled(false);
        carisup.setEnabled(false);
        caribrg.setEnabled(false);
        
        cariBT.setEnabled(true);
    }
    void cetak_nota(){
        try {
            String NamaFile = "src/laporan/notajual1.jasper";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/penjualan","root","");
            HashMap param = new HashMap();
            param.put("nota",noTF.getText());
            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, koneksi);
            JasperViewer.viewReport(JPrint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak dapat dicetak!","Cetak Data",JOptionPane.ERROR_MESSAGE);
        
    }

}
    private void cekstock(){
        try{
            setKoneksi();
            rs=st.executeQuery("SELECT *from barang where KodeBarang='" + kodeTF.getText() + "'");
            
            if (rs.next());
            kodeTF.setText(rs.getString("KodeBarang"));
            
            stockTF.setText(rs.getString("Stock"));
             } catch (Exception e) {
            
           

        }
    }
     private void simpan(){
 
        
        
        Integer a = Integer.parseInt(stockTF.getText());
        Integer b = Integer.parseInt(jumlahTF.getText());
        Integer c = a - b;
        stockTF.setText(String.valueOf(c));
        
            try{
            setKoneksi();
            String sql="update barang set Stock='"+stockTF.getText()+"' where KodeBarang='"+kodeTF.getText()+"'";
            st.executeUpdate(sql);
           
        } 
        catch(Exception e){
        
        }
       finally{
                
            }
        try{
            setKoneksi();
            String sql="insert into jual values('"+ noTF.getText() +"','" + tgl+"','" + supTF.getText()
                    + "','" + kodeTF.getText()+ "','" + namaTF.getText() + "','" + hjualTF.getText() + "','" + jumlahTF.getText()+"','" + thargaTF.getText()+"','" + bayarTF.getText()+"','" + kembaliTF.getText()+"')";
            st.executeUpdate(sql); 
            JOptionPane.showMessageDialog(null,"Simpan data berhasil");
            }
            catch (Exception e) {
        
        
    
        }
        tampil();
    }
    
    private void perbarui(){
        try{
            setKoneksi();
            String sql="update jual set TanggalTransaksi='"+tgl+"',Nama='"+supTF.getText()+"',KodeBarang='"+kodeTF.getText()+
                    "',NamaBarang='"+namaTF.getText()+ "',HargaJual='"+hjualTF.getText()+ "',Jumlah='"+jumlahTF.getText()+ "',TotalHarga='"+thargaTF.getText()+ "',UangBayar='"+bayarTF.getText()+ "',UangKembali='"+kembaliTF.getText()+"' where KodeTransaksi='"+noTF.getText()+"'";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Edit data berhasil","CV ANUGERAH SEJETERAH",JOptionPane.INFORMATION_MESSAGE);
        } 
        catch(Exception e){
        }
        
        tampil();
    }
    
    public void tampil(){
        Object header[]={"KodeTransaksi","TanggalTransaksi","Supplier","KodeBarang","NamaBarang","HargaJual","Jumlah","TotalHarga","UangBayar","UangKembali"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        jTable1.setModel(data);
        setKoneksi();
        String sql="select*from jual";
        try {
            ResultSet rs=st.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);
                String kolom6=rs.getString(6);
                 String kolom7=rs.getString(7);
                 String kolom8=rs.getString(8);
                String kolom9=rs.getString(9);
                 String kolom10=rs.getString(10);
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom8,kolom9,kolom10};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
    }
    
    private void hapus(){
        cekstock();
          Integer a = Integer.parseInt(stockTF.getText());
        Integer b = Integer.parseInt(jumlahTF.getText());
        Integer c = a +b;
        stockTF.setText(String.valueOf(c));
         try{
            setKoneksi();
            String sql="update barang set Stock='"+stockTF.getText()+"' where KodeBarang='"+kodeTF.getText()+"'";
            st.executeUpdate(sql);
           
        } 
        catch(Exception e){
        
        }
       finally{
         
         }
        try{
            
        
            String sql="delete from jual where KodeTransaksi='"+ noTF.getText() +"'";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Hapus data sukses");
            }
            catch (Exception e) {
            }
        tampil();
       
    }

private void otomatis(){
        try {
            setKoneksi();
            String sql="select right (KodeTransaksi,2)+1 from jual";
            ResultSet rs=st.executeQuery(sql);
            if(rs.next()){
                rs.last();
                String no=rs.getString(1);
                while (no.length()<3){
                    no="0"+no;
                    noTF.setText("TP"+no);}
                }
            else
            {
                noTF.setText("TP001"); 
        }
        } catch (Exception e) 
        {
        }
        
   
    }
private void tgl(){
    Date date = new Date();
        tanggalTF.setDate(date);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        noTF = new javax.swing.JTextField();
        supTF = new javax.swing.JTextField();
        tanggalTF = new com.toedter.calendar.JDateChooser();
        kodeTF = new javax.swing.JTextField();
        namaTF = new javax.swing.JTextField();
        jumlahTF = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        thargaTF = new javax.swing.JTextField();
        stockTF = new javax.swing.JTextField();
        carisup = new javax.swing.JButton();
        caribrg = new javax.swing.JButton();
        hjualTF = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        bayarTF = new javax.swing.JTextField();
        kembaliTF = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cariTF = new javax.swing.JTextField();
        cariBT = new javax.swing.JButton();
        tambahBT = new javax.swing.JButton();
        simpanBT = new javax.swing.JButton();
        hapusBT = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 620));
        jPanel1.setVerifyInputWhenFocusTarget(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("DATA TRANSAKSI PENJUALAN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(399, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(325, 325, 325))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1090, 50));

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 255), new java.awt.Color(204, 204, 255), new java.awt.Color(204, 204, 255), new java.awt.Color(204, 204, 255)));
        jPanel3.setAutoscrolls(true);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INPUT DATA PENJUALAN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel2.setText("NO TRANSAKSI");

        jLabel3.setText("TANGGAL TRASAKASI");

        jLabel4.setText("SUPPLIER");

        jLabel5.setText("KODE BARANG");

        jLabel6.setText("NAMA BARANG");

        jLabel7.setText("JUMLAH");

        jLabel8.setText("HARGA JUAL");

        tanggalTF.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tanggalTFPropertyChange(evt);
            }
        });

        kodeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeTFActionPerformed(evt);
            }
        });
        kodeTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kodeTFKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kodeTFKeyReleased(evt);
            }
        });

        namaTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaTFActionPerformed(evt);
            }
        });

        jumlahTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlahTFKeyReleased(evt);
            }
        });

        jLabel11.setText("TOTAL HARGA");

        thargaTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                thargaTFKeyReleased(evt);
            }
        });

        stockTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                stockTFKeyReleased(evt);
            }
        });

        carisup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Cari2.png"))); // NOI18N
        carisup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carisupActionPerformed(evt);
            }
        });

        caribrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Cari2.png"))); // NOI18N
        caribrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caribrgActionPerformed(evt);
            }
        });

        hjualTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hjualTFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(49, 49, 49)
                        .addComponent(noTF, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hjualTF, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(namaTF)
                                    .addComponent(kodeTF)
                                    .addComponent(supTF)
                                    .addComponent(thargaTF)
                                    .addComponent(tanggalTF, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jumlahTF, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stockTF, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(carisup)
                                    .addComponent(caribrg, javax.swing.GroupLayout.Alignment.TRAILING))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(noTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(tanggalTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(supTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carisup))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(caribrg))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(namaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(hjualTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jumlahTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(thargaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PROSES PEMBAYARAN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("UANG BAYAR");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("UANG KEMBALIAN");

        bayarTF.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bayarTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarTFActionPerformed(evt);
            }
        });
        bayarTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bayarTFKeyReleased(evt);
            }
        });

        kembaliTF.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        kembaliTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliTFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kembaliTF, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bayarTF, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(bayarTF, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(kembaliTF, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TABLE DATA PENJUALAN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        cariBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cariBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Cari2.png"))); // NOI18N
        cariBT.setText("CARI");
        cariBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariBTActionPerformed(evt);
            }
        });

        tambahBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tambahBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Tambah.png"))); // NOI18N
        tambahBT.setText("TAMBAH");
        tambahBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahBTActionPerformed(evt);
            }
        });

        simpanBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        simpanBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Simpan.png"))); // NOI18N
        simpanBT.setText("SIMPAN");
        simpanBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanBTActionPerformed(evt);
            }
        });

        hapusBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        hapusBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Hapus2.png"))); // NOI18N
        hapusBT.setText("BATAL");
        hapusBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(cariTF, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cariBT)))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(tambahBT)
                        .addGap(2, 2, 2)
                        .addComponent(simpanBT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hapusBT)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cariTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariBT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambahBT)
                    .addComponent(simpanBT)
                    .addComponent(hapusBT)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(126, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1080, 550));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void namaTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaTFActionPerformed

    private void hjualTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hjualTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hjualTFActionPerformed

    private void bayarTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bayarTFActionPerformed

    private void kembaliTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kembaliTFActionPerformed

    private void tambahBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBTActionPerformed
        // TODO add your handling code here:
        if(tambahBT.getText().equalsIgnoreCase("tambah")){
            tambahBT.setText("Batal");
            siapIsi(true);
            bersih();
            otomatis();
            namaTF.requestFocus();
            noTF.setEnabled(false);
            simpanBT.setEnabled(true);
            
            caribrg.setEnabled(true);
             carisup.setEnabled(true);
              cariBT.setEnabled(true);
        }else{
            bersih();
            siapIsi(false);
            tambahBT.setText("Tambah");
           
            tombolNormal();
        }
    }//GEN-LAST:event_tambahBTActionPerformed

    private void simpanBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanBTActionPerformed
        // TODO add your handling code here:
        if(supTF.getText().isEmpty()||tgl.equals("")||kodeTF.getText().isEmpty()||namaTF.getText().isEmpty() ||hjualTF.getText().isEmpty()||jumlahTF.getText().isEmpty()||thargaTF.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Lengkapi inputan penjualan barang");
        } else{
            simpan();  
            int pesan=JOptionPane.showConfirmDialog(null, "Cetak Kwitansi Nota","Cetak",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
               
            if(pesan==JOptionPane.YES_OPTION){
                   cetak_nota();
               }else {
                   JOptionPane.showMessageDialog(null, "Simpan Transaksi Berhasil");
               }
            
        

            bersih();
            
            tambahBT.setText("Tambah");
           siapIsi(false);
            tombolNormal();
        }
    }//GEN-LAST:event_simpanBTActionPerformed

    private void hapusBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBTActionPerformed
        // TODO add your handling code here:
         int pesan=JOptionPane.showConfirmDialog(null, "Yakin BATALKAN TRANSAKSI PENJUALAN ?","Konfirmasi",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(pesan==JOptionPane.YES_OPTION){
            if(pesan==JOptionPane.YES_OPTION){
                hapus();
                bersih();
                siapIsi(false);
                tombolNormal();
            } else{
                JOptionPane.showMessageDialog(null, "Hapus data gagal");
            }
            tampil();
        }
    }//GEN-LAST:event_hapusBTActionPerformed

    private void carisupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carisupActionPerformed
        // TODO add your handling code here:
        boolean closable = true;
        sup fDB = new sup(null, closable);
        fDB.fAB = this;
        fDB.setVisible(true);
        fDB.setResizable(true);
        
        supTF.setText(Namasu);
    }//GEN-LAST:event_carisupActionPerformed

    private void kodeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodeTFActionPerformed

    private void caribrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caribrgActionPerformed
        // TODO add your handling code here:
        boolean closable = true;
        brg fDB = new brg(null, closable);
        fDB.fAB = this;
        fDB.setVisible(true);
        fDB.setResizable(true);
         kodeTF.setText(Kodeba);
        namaTF.setText(Namaba);
         hjualTF.setText(Hargaju);
         stockTF.setText(stock);
         
    }//GEN-LAST:event_caribrgActionPerformed

    private void jumlahTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahTFKeyReleased
        // TODO add your handling code here:
        Integer stock=Integer.parseInt(stockTF.getText());
        Integer a = Integer.parseInt(hjualTF.getText());
        Integer b = Integer.parseInt(jumlahTF.getText());
        if(b>stock){
             JOptionPane.showMessageDialog(null, "Stok barang tidak mencukupi");
             
             }else{
        Integer c = a * b;
        thargaTF.setText(String.valueOf(c));
        
        }
    }//GEN-LAST:event_jumlahTFKeyReleased

    private void thargaTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_thargaTFKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_thargaTFKeyReleased

    private void bayarTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayarTFKeyReleased
        // TODO add your handling code here:
        Integer a = Integer.parseInt(bayarTF.getText());
        Integer b = Integer.parseInt(thargaTF.getText());
        Integer c = a - b;
        kembaliTF.setText(String.valueOf(c));
    }//GEN-LAST:event_bayarTFKeyReleased

    private void tanggalTFPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tanggalTFPropertyChange
        // TODO add your handling code here:
        try{
            if(tanggalTF.getDate()!=null){
                String pattern="yyyy-MM-dd";
                SimpleDateFormat format =new SimpleDateFormat(pattern);
                tgl=String.valueOf(format.format(tanggalTF.getDate()));
            }
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_tanggalTFPropertyChange

    private void stockTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stockTFKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_stockTFKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         int baris =jTable1.rowAtPoint(evt.getPoint());
         String NoTransaksi=jTable1.getValueAt(baris,0).toString();
         noTF.setText(NoTransaksi);
          try{
        String TanggalPeminjaman=jTable1.getValueAt(baris, 1).toString();
        SimpleDateFormat f =new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d=f.parse(TanggalPeminjaman);
        tanggalTF.setDate(d);
        }catch(Exception e){
            e.printStackTrace();
      }  
          hapusBT.setEnabled(true);
            
          String Supplier =jTable1.getValueAt(baris, 2).toString();
        supTF.setText(Supplier);
        String KodeBarang =jTable1.getValueAt(baris, 3).toString();
        kodeTF.setText(KodeBarang);
        String NamaBarang =jTable1.getValueAt(baris, 4).toString();
        namaTF.setText(NamaBarang);
        
        String  HargaJual=jTable1.getValueAt(baris, 5).toString();
        hjualTF.setText(HargaJual);
        
        String Jumlah =jTable1.getValueAt(baris, 6).toString();
        jumlahTF.setText(Jumlah);
         String TotalHarga =jTable1.getValueAt(baris, 7).toString();
        thargaTF.setText(TotalHarga);
        String UangBayar =jTable1.getValueAt(baris, 8).toString();
        bayarTF.setText(UangBayar);
         String UangKembali =jTable1.getValueAt(baris, 9).toString();
        kembaliTF.setText(UangKembali);
        
          
    }//GEN-LAST:event_jTable1MouseClicked

    private void cariBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariBTActionPerformed
        // TODO add your handling code here:
        Object header[]={"KodeTransaksi","TanggalTransaksi","Nama","KodeBarang","NamaBarang","HargaJual","Jumlah","TotalHarga","UangBayar","UangKembali"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        jTable1.setModel(data);
        setKoneksi();
        String sql="Select * from jual where KodeTransaksi like '%" + cariTF.getText() + "%'" + "or NamaBarang like '%" + cariTF.getText()+ "%'";
        try {
            ResultSet rs=st.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);
                 String kolom6=rs.getString(6);
                String kolom7=rs.getString(7);
                String kolom8=rs.getString(8);
                 String kolom9=rs.getString(9);
                String kolom10=rs.getString(10);
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom8,kolom9,kolom10};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_cariBTActionPerformed

    private void kodeTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodeTFKeyReleased
        // TODO add your handling code here:
        try{
            setKoneksi();
            rs=st.executeQuery("SELECT *from barang where KodeBarang='" + kodeTF.getText() + "'");
            
            if (rs.next());
            kodeTF.setText(rs.getString("KodeBarang"));
            
            stockTF.setText(rs.getString("Stock"));
             } catch (Exception e) {
            
           

        }
    }//GEN-LAST:event_kodeTFKeyReleased

    private void kodeTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodeTFKeyPressed
        // TODO add your handling code here:
         try{
            setKoneksi();
            rs=st.executeQuery("SELECT *from barang where KodeBarang='" + kodeTF.getText() + "'");
            
            if (rs.next());
            kodeTF.setText(rs.getString("KodeBarang"));
            
            stockTF.setText(rs.getString("Stock"));
             } catch (Exception e) {
            
           

        }
    }//GEN-LAST:event_kodeTFKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bayarTF;
    private javax.swing.JButton cariBT;
    private javax.swing.JTextField cariTF;
    private javax.swing.JButton caribrg;
    private javax.swing.JButton carisup;
    private javax.swing.JButton hapusBT;
    private javax.swing.JTextField hjualTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jumlahTF;
    private javax.swing.JTextField kembaliTF;
    private javax.swing.JTextField kodeTF;
    private javax.swing.JTextField namaTF;
    private javax.swing.JTextField noTF;
    private javax.swing.JButton simpanBT;
    private javax.swing.JTextField stockTF;
    private javax.swing.JTextField supTF;
    private javax.swing.JButton tambahBT;
    private com.toedter.calendar.JDateChooser tanggalTF;
    private javax.swing.JTextField thargaTF;
    // End of variables declaration//GEN-END:variables
public Connection setKoneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/penjualan","root","");
            st=conn.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Koneksi Gagal :" +e);
        }
       return conn; 
    }

   


}


