/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dc
 */
public class bbarang extends javax.swing.JInternalFrame {
Connection conn;
    Statement st;
    ResultSet rs;
    PreparedStatement pst;
String tgl;
    
    /**
     * Creates new form bbarang
     */
    public bbarang() {
        initComponents();
         ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
    stockTF.hide();
        otomatis();
    siapIsi(false);
        tombolNormal();
        tampil();
       
    }
    public String Kodeba, Namaba, Hargabe, stock;

    public String Kodebar() {
        return Kodeba;
    }

    ;
    public String Namabar() {
        return Namaba;
    }

    ;
    public String Hargabel() {
        return Hargabe;
    }

    ;
    public String stock() {
        return stock;
    }
    
private void bersih(){
        noTF.setText("");
        kodeTF.setText("");
        namaTF.setText("");
        hbeliTF.setText("0");
        tanggalTF.setDate(null);
        jumlahTF.setText("");
        thargaTF.setText("0");
        stockTF.setText("");
    }

    private void siapIsi(boolean a){
       noTF.setEnabled(a);
        kodeTF.setEnabled(a);
        namaTF.setEnabled(a);
        tanggalTF.setEnabled(a);
        hbeliTF.setEnabled(a);
        stockTF.setEnabled(a);
        jumlahTF.setEnabled(a);
        thargaTF.setEnabled(a);
        
    }
    
    private void tombolNormal(){
        tambahBT.setEnabled(true);
        simpanBT.setEnabled(false);
       caribrg.setEnabled(false);
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
        Integer c = a + b;
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
            String sql="insert into beli values('"+ noTF.getText() +"','" + kodeTF.getText()+"','" + namaTF.getText()
                    + "','" + tgl+ "','" + hbeliTF.getText() + "','" + jumlahTF.getText() + "','" + thargaTF.getText()+"')";
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
            String sql="update beli set KodeBarang='"+kodeTF.getText()+"',NamaBarang='"+namaTF.getText()+"',TanggalBeli='"+tgl+
                    "',HargaBeli='"+hbeliTF.getText()+ "',Jumlah='"+jumlahTF.getText()+ "',TotalHarga='"+thargaTF.getText()+"' where NoBeli='"+noTF.getText()+"'";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Edit data berhasil","CV ANUGERAH SEJETERAH",JOptionPane.INFORMATION_MESSAGE);
        } 
        catch(Exception e){
        }
        
        tampil();
    }
    
    public void tampil(){
        Object header[]={"NoBeli","KodeBarang","NamaBarang","TanggalBeli","HargaBeli","Jumlah","TotalHarga"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        jTable1.setModel(data);
        setKoneksi();
        String sql="select*from beli";
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
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
    }
    
    private void hapus(){
        cekstock();
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
       
        setKoneksi();
        try{
            String sql="delete from beli where NoBeli='"+ noTF.getText() +"'";
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
            String sql="select right (NoBeli,2)+1 from beli";
            ResultSet rs=st.executeQuery(sql);
            if(rs.next()){
                rs.last();
                String no=rs.getString(1);
                while (no.length()<3){
                    no="0"+no;
                    noTF.setText("P"+no);}
                }
            else
            {
                noTF.setText("P001"); 
        }
        } catch (Exception e) 
        {
        }
        
   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        kodeTF = new javax.swing.JTextField();
        hbeliTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        stockTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jumlahTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        thargaTF = new javax.swing.JTextField();
        namaTF = new javax.swing.JTextField();
        caribrg = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        noTF = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cariTF = new javax.swing.JTextField();
        cariBT = new javax.swing.JButton();
        tambahBT = new javax.swing.JButton();
        simpanBT = new javax.swing.JButton();
        hapusBT = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 620));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("TRANSAKSI PEMBELIAN BARANG");

        jPanel4.setBackground(new java.awt.Color(51, 153, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 255), new java.awt.Color(204, 204, 255), new java.awt.Color(204, 204, 255), new java.awt.Color(204, 204, 255)));
        jPanel4.setToolTipText("");

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INPUT PEMBELIAN BARANG", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel2.setText("KODE BARANG");

        jLabel3.setText("NAMA BARANG");

        jLabel4.setText("HARGA BELI");

        kodeTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kodeTFKeyReleased(evt);
            }
        });

        hbeliTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hbeliTFActionPerformed(evt);
            }
        });

        jLabel5.setText("TANNGAL BELI");

        stockTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stockTFMouseClicked(evt);
            }
        });
        stockTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockTFActionPerformed(evt);
            }
        });
        stockTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                stockTFKeyReleased(evt);
            }
        });

        jLabel6.setText("JUMLAH");

        jumlahTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahTFActionPerformed(evt);
            }
        });
        jumlahTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlahTFKeyReleased(evt);
            }
        });

        jLabel7.setText("TOTAL HARGA");

        thargaTF.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        caribrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Cari2.png"))); // NOI18N
        caribrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caribrgActionPerformed(evt);
            }
        });

        jLabel8.setText("NO TRANSAKSI");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(thargaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jumlahTF, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stockTF, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(hbeliTF, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(kodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(caribrg))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(noTF, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                        .addComponent(namaTF))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(caribrg, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(kodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(hbeliTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jumlahTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap(32, Short.MAX_VALUE))
                    .addComponent(thargaTF)))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TABLE DATA PEMBELIAN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cariTF, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariBT)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cariTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariBT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(tambahBT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(simpanBT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hapusBT))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(216, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambahBT)
                    .addComponent(simpanBT)
                    .addComponent(hapusBT))
                .addContainerGap(575, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(593, 593, 593))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(278, 278, 278)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(46, 46, 46))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hapusBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBTActionPerformed
        // TODO add your handling code here:
        int pesan=JOptionPane.showConfirmDialog(null, "Yakin data akan dihapus ?","Konfirmasi",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
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

    private void hbeliTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hbeliTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hbeliTFActionPerformed

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
        }else{
            bersih();
            siapIsi(false);
            tambahBT.setText("Tambah");
          
            tombolNormal();
        }
    }//GEN-LAST:event_tambahBTActionPerformed

    private void simpanBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanBTActionPerformed
        // TODO add your handling code here:
        if(kodeTF.getText().isEmpty() ||namaTF.getText().isEmpty()||tgl.equals("") ||hbeliTF.getText().isEmpty()||jumlahTF.getText().isEmpty()||thargaTF.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Lengkapi inputan data","CV ANUGERAH SEJETERAH",JOptionPane.INFORMATION_MESSAGE);
        } else{

            if(tambahBT.getText().equalsIgnoreCase("batal")){
                if(tambahBT.getText().equalsIgnoreCase("batal")){
                    simpan();
                } else{
                    JOptionPane.showMessageDialog(null, "Simpan data gagal, periksa kembali","CV ANUGERAH SEJETERAH",JOptionPane.INFORMATION_MESSAGE);
                }
            
            
            }
            bersih();
            
            siapIsi(false);
            
            tombolNormal();
        }
    }//GEN-LAST:event_simpanBTActionPerformed

    private void jumlahTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahTFKeyReleased
        // TODO add your handling code here:
         Integer a = Integer.parseInt(hbeliTF.getText());
        Integer b = Integer.parseInt(jumlahTF.getText());
        Integer c = a * b;
        thargaTF.setText(String.valueOf(c));
    }//GEN-LAST:event_jumlahTFKeyReleased

    private void kodeTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodeTFKeyReleased
        // TODO add your handling code here:
        
            
        
    }//GEN-LAST:event_kodeTFKeyReleased

    private void caribrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caribrgActionPerformed
        // TODO add your handling code here:
       boolean closable = true;
        pgl fDB = new pgl(null, closable);
        fDB.fAB = this;
        fDB.setVisible(true);
        fDB.setResizable(true);
        kodeTF.setText(Kodeba);
        namaTF.setText(Namaba);
        hbeliTF.setText(Hargabe);
        stockTF.setText(stock);
    }//GEN-LAST:event_caribrgActionPerformed

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

    private void stockTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockTFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_stockTFMouseClicked

    private void stockTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stockTFKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_stockTFKeyReleased

    private void stockTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stockTFActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         int baris =jTable1.rowAtPoint(evt.getPoint());
         String NoBeli=jTable1.getValueAt(baris,0).toString();
         noTF.setText(NoBeli);
         
        String KodeBarang =jTable1.getValueAt(baris, 1).toString();
        kodeTF.setText(KodeBarang);
        String NamaBarang =jTable1.getValueAt(baris, 2).toString();
        namaTF.setText(NamaBarang);
        try{
        String TanggalPeminjaman=jTable1.getValueAt(baris, 3).toString();
        SimpleDateFormat f =new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d=f.parse(TanggalPeminjaman);
        tanggalTF.setDate(d);
        }catch(Exception e){
            e.printStackTrace();
      }  
        String  HargaBeli=jTable1.getValueAt(baris, 4).toString();
        hbeliTF.setText(HargaBeli);
        String Jumlah =jTable1.getValueAt(baris, 5).toString();
        jumlahTF.setText(Jumlah);
         String TotalHarga =jTable1.getValueAt(baris, 6).toString();
        thargaTF.setText(TotalHarga);
          hapusBT.setEnabled(true);
            
           
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jumlahTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahTFActionPerformed

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MouseClicked

    private void cariBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariBTActionPerformed
        // TODO add your handling code here:
        Object header[]={"KodeBeli","KodeBarang","NamaBarang","TanggalBeli","HargaJual","Jumlah","TotalHarga"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        jTable1.setModel(data);
        setKoneksi();
        String sql="Select * from beli where NoBeli like '%" + cariTF.getText() + "%'" + "or NamaBarang like '%" + cariTF.getText()+ "%'";
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
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cariBTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cariBT;
    private javax.swing.JTextField cariTF;
    private javax.swing.JButton caribrg;
    private javax.swing.JButton hapusBT;
    private javax.swing.JTextField hbeliTF;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jumlahTF;
    private javax.swing.JTextField kodeTF;
    private javax.swing.JTextField namaTF;
    private javax.swing.JTextField noTF;
    private javax.swing.JButton simpanBT;
    private javax.swing.JTextField stockTF;
    private javax.swing.JButton tambahBT;
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

