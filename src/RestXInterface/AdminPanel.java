/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestXInterface;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import net.proteanit.sql.DbUtils;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class AdminPanel extends javax.swing.JFrame {
    
    /* These variables are to obtain the location of the window and make
     * them movable as they are undecorated/.
    */
    int pX, pY;
    PreparedStatement p_st;
    ResultSet rs;
    

    public AdminPanel() {
        initComponents();
        
        this.setSize(834, 487);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        // Sort and add item to the admin data table
        // addDataToTableAvail();
        jTable_admin_data.setDefaultEditor(Object.class, null);
        jTable_admin_data.setAutoCreateRowSorter(true);
        showAdminTable();

//        *******************************************************************************
//        This commented row sorter is making all the rows to null after execution
//        
//        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel)jTable_admin_data.getModel());
//        jTable_admin_data.setRowSorter(sorter);
//        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
//
//        int columnIndexToSort = 0;
//        sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
//
//        sorter.setSortKeys(sortKeys);
//        sorter.sort();
//        
//    ***********************************************************************************
//        showAdminTable();
    }

    
//    private void addDataToTableAvail () {
//
//        // Creating a select query to check the existence of username and password in the database
//        String query = "SELECT `category` AS 'Category', `item_menu` AS 'Items', `price` AS 'Price', `discount` AS 'Discount' FROM `admin_data`";
//        try {
//            p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
//            rs = p_st.executeQuery();
//            jTable_admin_data.setModel(DbUtils.resultSetToTableModel(rs));
//            p_st.close();
//        } catch (SQLException e) {
//            System.err.println("SQL Exception: " + e.toString());
//        }
//    }
    
//    class Product {
//        private String cat, item, price, disc;
//        Product (String cat, String item, String price, String disc) {
//            this.cat = cat;
//            this.item = item;
//            this.price = price;
//            this.disc = disc;
//        }
//        public String getCat() {
//            return cat;
//        }
//        public void setCat(String cat) {
//            this.cat = cat;
//        }
//        public String getItem() {
//            return item;
//        }
//        public void setItem(String item) {
//            this.item = item;
//        }
//        public String getPrice() {
//            return price;
//        }
//        public void setPrice(String price) {
//            this.price = price;
//        }
//        public String getDisc() {
//            return disc;
//        }
//        public void setDisc(String disc) {
//            this.disc = disc;
//        }
//    }
//    
//    private ArrayList<Product> getData() {
//        ArrayList<Product> p = new ArrayList<>();
//        String query = "SELECT `category` AS 'Category', `item_menu` AS 'Items', `price` AS 'Price', `discount` AS 'Discount' FROM `admin_data`";
//        
//        try {
//            p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
//            rs = p_st.executeQuery();
//            
//            Product newP;
//            while (rs.next()) {
//                newP = new Product(rs.getString("Category"), rs.getString("Items"), rs.getString("Price"), rs.getString("Discount"));
//                p.add(newP);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return p;
//    }
//    
//    private void showAdminTable() {
//        
//        ArrayList<Product> newP = getData();
//        DefaultTableModel model = (DefaultTableModel)jTable_admin_data.getModel();
//        Object[] row = new Object[4];
//        for (int i = 0; i < newP.size(); i++) {
//            row[0] = newP.get(i).getCat();
//            row[1] = newP.get(i).getItem();
//            row[2] = newP.get(i).getPrice();
//            row[3] = newP.get(i).getDisc();
//            model.addRow(row);
//        }
//        jTable_admin_data.setModel(model);
//    }
    
    public void showAdminTable() {
        String query = "SELECT `category` AS 'Category', `item_menu` AS 'Items', `price` AS 'Price', `discount` AS 'Discou' FROM `admin_data`";
        try {
            p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
            rs = p_st.executeQuery();
            jTable_admin_data.setModel(DbUtils.resultSetToTableModel(rs));
            p_st.close();
        } catch(SQLException e) {
            Logger.getLogger(e.toString());
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

        jLabel_restxname = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_qsearch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_admin_data = new javax.swing.JTable();
        jLabel_cat = new javax.swing.JLabel();
        jLabel_item = new javax.swing.JLabel();
        jLabel_price = new javax.swing.JLabel();
        jLabel_disc = new javax.swing.JLabel();
        jLabel_insert = new javax.swing.JLabel();
        jLabel_update = new javax.swing.JLabel();
        jLabel_delete = new javax.swing.JLabel();
        jLabel_clear = new javax.swing.JLabel();
        jTextField_disc = new javax.swing.JTextField();
        jTextField_item = new javax.swing.JTextField();
        jTextField_price = new javax.swing.JTextField();
        jTextField_cat = new javax.swing.JTextField();
        jLabel_go_back_to_main = new javax.swing.JLabel();
        jLabel_minimize = new javax.swing.JLabel();
        jLabel_close = new javax.swing.JLabel();
        jLabel_bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel_restxname.setBackground(new java.awt.Color(153, 255, 153));
        jLabel_restxname.setFont(new java.awt.Font("Tahoma", 1, 40)); // NOI18N
        jLabel_restxname.setForeground(new java.awt.Color(204, 0, 102));
        jLabel_restxname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_restxname.setText("Welcome to RestX");
        jLabel_restxname.setName(""); // NOI18N
        getContentPane().add(jLabel_restxname);
        jLabel_restxname.setBounds(50, 0, 700, 70);
        jLabel_restxname.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search_icn.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(0, 204, 204))));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2);
        jLabel2.setBounds(760, 110, 30, 30);

        jTextField_qsearch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField_qsearch.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_qsearch.setText("Quick Search by Item Name");
        jTextField_qsearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jTextField_qsearch.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jTextField_qsearchComponentAdded(evt);
            }
        });
        jTextField_qsearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_qsearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_qsearchFocusLost(evt);
            }
        });
        jTextField_qsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_qsearchActionPerformed(evt);
            }
        });
        jTextField_qsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_qsearchKeyReleased(evt);
            }
        });
        getContentPane().add(jTextField_qsearch);
        jTextField_qsearch.setBounds(410, 110, 380, 30);

        jTable_admin_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Category", "Items", "Price", "Discount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_admin_data.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jTable_admin_data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_admin_dataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_admin_data);
        if (jTable_admin_data.getColumnModel().getColumnCount() > 0) {
            jTable_admin_data.getColumnModel().getColumn(0).setResizable(false);
            jTable_admin_data.getColumnModel().getColumn(1).setResizable(false);
            jTable_admin_data.getColumnModel().getColumn(2).setResizable(false);
            jTable_admin_data.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(410, 150, 380, 300);

        jLabel_cat.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel_cat.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_cat.setText("Category");
        getContentPane().add(jLabel_cat);
        jLabel_cat.setBounds(60, 160, 74, 30);

        jLabel_item.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel_item.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_item.setText("Item");
        getContentPane().add(jLabel_item);
        jLabel_item.setBounds(90, 210, 40, 30);

        jLabel_price.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel_price.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_price.setText("Price");
        getContentPane().add(jLabel_price);
        jLabel_price.setBounds(90, 260, 50, 30);

        jLabel_disc.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel_disc.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_disc.setText("Discount");
        getContentPane().add(jLabel_disc);
        jLabel_disc.setBounds(60, 310, 80, 30);

        jLabel_insert.setBackground(new java.awt.Color(51, 255, 51));
        jLabel_insert.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_insert.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_insert.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_insert.setText("INSERT");
        jLabel_insert.setOpaque(true);
        jLabel_insert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_insertMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_insertMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_insertMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_insertMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_insertMouseReleased(evt);
            }
        });
        getContentPane().add(jLabel_insert);
        jLabel_insert.setBounds(60, 360, 100, 40);

        jLabel_update.setBackground(new java.awt.Color(51, 255, 204));
        jLabel_update.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_update.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_update.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_update.setText("UPDATE");
        jLabel_update.setOpaque(true);
        jLabel_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_updateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_updateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_updateMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_updateMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_updateMouseReleased(evt);
            }
        });
        getContentPane().add(jLabel_update);
        jLabel_update.setBounds(170, 360, 100, 40);

        jLabel_delete.setBackground(new java.awt.Color(255, 102, 0));
        jLabel_delete.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_delete.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_delete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_delete.setText("DELETE");
        jLabel_delete.setOpaque(true);
        jLabel_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_deleteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_deleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_deleteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_deleteMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_deleteMouseReleased(evt);
            }
        });
        getContentPane().add(jLabel_delete);
        jLabel_delete.setBounds(120, 410, 100, 40);

        jLabel_clear.setBackground(new java.awt.Color(51, 51, 51));
        jLabel_clear.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_clear.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_clear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_clear.setText("CLEAR");
        jLabel_clear.setOpaque(true);
        jLabel_clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_clearMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_clearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_clearMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_clearMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_clearMouseReleased(evt);
            }
        });
        getContentPane().add(jLabel_clear);
        jLabel_clear.setBounds(280, 360, 100, 40);

        jTextField_disc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(jTextField_disc);
        jTextField_disc.setBounds(150, 300, 230, 40);

        jTextField_item.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(jTextField_item);
        jTextField_item.setBounds(150, 200, 230, 40);

        jTextField_price.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(jTextField_price);
        jTextField_price.setBounds(150, 250, 230, 40);

        jTextField_cat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(jTextField_cat);
        jTextField_cat.setBounds(150, 150, 230, 40);

        jLabel_go_back_to_main.setBackground(new java.awt.Color(204, 153, 0));
        jLabel_go_back_to_main.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 14)); // NOI18N
        jLabel_go_back_to_main.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_go_back_to_main.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_go_back_to_main.setText("Go to Main Menu");
        jLabel_go_back_to_main.setOpaque(true);
        jLabel_go_back_to_main.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_go_back_to_mainMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_go_back_to_mainMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_go_back_to_mainMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_go_back_to_mainMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_go_back_to_mainMouseReleased(evt);
            }
        });
        getContentPane().add(jLabel_go_back_to_main);
        jLabel_go_back_to_main.setBounds(230, 410, 150, 40);

        jLabel_minimize.setBackground(new java.awt.Color(204, 204, 255));
        jLabel_minimize.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel_minimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_minimize.setText("-");
        jLabel_minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseReleased(evt);
            }
        });
        getContentPane().add(jLabel_minimize);
        jLabel_minimize.setBounds(740, 10, 40, 30);

        jLabel_close.setBackground(new java.awt.Color(153, 0, 0));
        jLabel_close.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel_close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_close.setText("X");
        jLabel_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_closeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseReleased(evt);
            }
        });
        getContentPane().add(jLabel_close);
        jLabel_close.setBounds(780, 10, 40, 30);

        jLabel_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/admin_bg_1.jpg"))); // NOI18N
        getContentPane().add(jLabel_bg);
        jLabel_bg.setBounds(-360, -40, 1270, 630);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        pX = evt.getX();
        pY = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        this.setLocation(this.getX() + evt.getX() - pX, this.getY() + evt.getY() - pY);
    }//GEN-LAST:event_formMouseDragged

    private void jTextField_qsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_qsearchKeyReleased

        String query = "SELECT `category` AS 'Category', `item_menu` AS 'Items', `price` AS 'Price', `discount` AS 'Discount' FROM `admin_data` WHERE `item_menu` LIKE '%" +
        jTextField_qsearch.getText() + "%'";
        try {
            p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
            rs = p_st.executeQuery();
            jTable_admin_data.setModel(DbUtils.resultSetToTableModel(rs));            
        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(CustomerPortal.class.getName()).log(Level.SEVERE, null, ex);
        }
//        DefaultTableModel model = (DefaultTableModel)jTable_admin_data.getModel();
//        String search = jTextField_qsearch.getText().toLowerCase();
//        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
//        jTable_admin_data.setRowSorter(tr);
//        tr.setRowFilter(RowFilter.regexFilter(search + "*"));
        


//        DefaultTableModel model = (DefaultTableModel)jTable_admin_data.getModel();
//        String search = jTextField_qsearch.getText().toLowerCase();
//        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
//        jTable_admin_data.setRowSorter(tr);
//        tr.setRowFilter(RowFilter.regexFilter(search + "*"));
        
        
    }//GEN-LAST:event_jTextField_qsearchKeyReleased

    private void jTextField_qsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_qsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_qsearchActionPerformed

    private void jTextField_qsearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_qsearchFocusLost
        if (jTextField_qsearch.getText().equals("")) {
            jTextField_qsearch.setText("Quick Search by Item Name");
            jTextField_qsearch.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_jTextField_qsearchFocusLost

    private void jTextField_qsearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_qsearchFocusGained
        if (jTextField_qsearch.getText().toLowerCase().equals("quick search by item name")) {
            jTextField_qsearch.setText("");
            jTextField_qsearch.setForeground(Color.black);
        }
    }//GEN-LAST:event_jTextField_qsearchFocusGained

    private void jTextField_qsearchComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jTextField_qsearchComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_qsearchComponentAdded

    private void jTable_admin_dataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_admin_dataMouseClicked
//        PreparedStatement p_st;
//         ResultSet rs;
//        try {
            int i = jTable_admin_data.getSelectedRow();
//            TableModel model = jTable_admin_data.getModel();
//            String item = (model.getValueAt(i, 0).toString());
            

                    jTextField_cat.setText(jTable_admin_data.getValueAt(i, 0).toString());
                    jTextField_item.setText(jTable_admin_data.getValueAt(i, 1).toString());
                    jTextField_price.setText(jTable_admin_data.getValueAt(i, 2).toString());
                    jTextField_disc.setText(jTable_admin_data.getValueAt(i, 3).toString());
//
//        } catch (SQLException ex) {
//            Logger.getLogger(CustomerPortal.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jTable_admin_dataMouseClicked

    private void jLabel_clearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_clearMouseClicked
        jTextField_cat.setText("");
        jTextField_item.setText("");
        jTextField_price.setText("");
        jTextField_disc.setText("");
    }//GEN-LAST:event_jLabel_clearMouseClicked

    private void jLabel_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_updateMouseClicked
               
        try {
            int i = jTable_admin_data.getSelectedRow();
            String upd_cat = "UPDATE `admin_data` SET `category`= ?, `item_menu` = ?, `price` = ?, `discount` = ? WHERE `item_menu` = '" + jTable_admin_data.getValueAt(i, 1).toString() + "'";
                        
            p_st = DBConnect.DBConnect.getConnection().prepareStatement(upd_cat);
            p_st.setString(1, jTextField_cat.getText());
            p_st.setString(2, jTextField_item.getText());
            p_st.setString(3, jTextField_price.getText());
            p_st.setString(4, jTextField_disc.getText());
            
//            Validation checking
            if (jTextField_cat.getText().matches(".*[0-9].*")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid category.");
                return;
            }
            if (jTextField_cat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid category.");
                return;
            }
            if (jTextField_item.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid item.");
                return;
            }
            if (jTextField_price.getText().matches(".*[a-zA-Z].*")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid price.");
                return;
            }
            if (jTextField_disc.getText().matches(".*[a-zA-Z].*")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid discount.");
                return;
            }
            
            int j = p_st.executeUpdate();
            if (j > 0) {
                JOptionPane.showMessageDialog(null, "Data Updated Successfully.");
            }
            
            jTextField_cat.setText("");
            jTextField_item.setText("");
            jTextField_price.setText("");
            jTextField_disc.setText("");
            
            // Refreshing the Admin Table after modification
//            DefaultTableModel model = (DefaultTableModel)jTable_admin_data.getModel();
//            model.setRowCount(0);
            showAdminTable();
            
        } catch (IndexOutOfBoundsException e) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Please select a data!", "Error!", 1);
        }catch (SQLException ex) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data Error!", "Error!", 1);
        } 
    }//GEN-LAST:event_jLabel_updateMouseClicked

    private void jLabel_insertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_insertMouseClicked
        String query = "INSERT INTO `admin_data`(`category`, `item_menu`, `price`, `discount`) VALUES ('" + jTextField_cat.getText() + "', '" + jTextField_item.getText() + "', '" + jTextField_price.getText() + "', '" + jTextField_disc.getText() + "')";
        
        try {
            PreparedStatement p_st_ins;
            p_st_ins = DBConnect.DBConnect.getConnection().prepareStatement(query);
            if (jTextField_cat.getText().matches(".*[0-9].*")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid category.");
                return;
            }
            if (jTextField_cat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid category.");
                return;
            }
            if (jTextField_item.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid item.");
                return;
            }
            if (jTextField_price.getText().matches(".*[a-zA-Z].*")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid price.");
                return;
            }
            if (jTextField_disc.getText().matches(".*[a-zA-Z].*")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid discount.");
                return;
            }
            int i = p_st_ins.executeUpdate();
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Data Entered Successfully.");
            }
            
            jTextField_cat.setText("");
            jTextField_item.setText("");
            jTextField_price.setText("");
            jTextField_disc.setText("");
            
            // Refreshing the Admin Table after modification
//            DefaultTableModel model = (DefaultTableModel)jTable_admin_data.getModel();
//            model.setRowCount(0);
            showAdminTable();
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Check, either price and discount are empty or not valid!", "Data Error!", 1);
        }
    }//GEN-LAST:event_jLabel_insertMouseClicked

    private void jLabel_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_deleteMouseClicked
        try {
            int selected_row = jTable_admin_data.getSelectedRow();
            String query = "DELETE FROM `admin_data` WHERE `item_menu` = '" + jTextField_item.getText() + "'";
            if (selected_row > 0) {
                p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
                int i = p_st.executeUpdate();
                if (i == 1) {
                    JOptionPane.showMessageDialog(null, "Data Deleted Successfully.");
                }

                jTextField_cat.setText("");
                jTextField_item.setText("");
                jTextField_price.setText("");
                jTextField_disc.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Please select an item from table.", "Selection error!", 2);
            }
            // Refreshing the Admin Table after modification
//            DefaultTableModel model = (DefaultTableModel)jTable_admin_data.getModel();
//            model.setRowCount(0);
            showAdminTable();
         } catch (SQLException ex) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data Error!", "Error!", 1);
        }
    }//GEN-LAST:event_jLabel_deleteMouseClicked

    private void jLabel_go_back_to_mainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_go_back_to_mainMouseClicked
        new RestXInterface.FirstWelcomeScrn().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel_go_back_to_mainMouseClicked

    private void jLabel_minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseClicked
        this.setState(FirstWelcomeScrn.ICONIFIED);
    }//GEN-LAST:event_jLabel_minimizeMouseClicked

    private void jLabel_minimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseEntered
        jLabel_minimize.setBackground(Color.gray);
        jLabel_minimize.setOpaque(true);
    }//GEN-LAST:event_jLabel_minimizeMouseEntered

    private void jLabel_minimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseExited
        jLabel_minimize.setBackground(new Color(0,0,0,0));
        jLabel_minimize.setOpaque(false);
    }//GEN-LAST:event_jLabel_minimizeMouseExited

    private void jLabel_minimizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMousePressed
        jLabel_minimize.setBackground(Color.darkGray);
    }//GEN-LAST:event_jLabel_minimizeMousePressed

    private void jLabel_minimizeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseReleased
        jLabel_minimize.setBackground(new Color(0,0,0,0));
    }//GEN-LAST:event_jLabel_minimizeMouseReleased

    private void jLabel_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel_closeMouseClicked

    private void jLabel_closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseEntered
        jLabel_close.setBackground(Color.red);
        jLabel_close.setOpaque(true);
    }//GEN-LAST:event_jLabel_closeMouseEntered

    private void jLabel_closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseExited
        jLabel_close.setBackground(new Color(0,0,0,0));
        jLabel_close.setOpaque(false);
    }//GEN-LAST:event_jLabel_closeMouseExited

    private void jLabel_closeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMousePressed

        jLabel_close.setBackground(new Color(153,0,0));
    }//GEN-LAST:event_jLabel_closeMousePressed

    private void jLabel_closeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseReleased
        jLabel_close.setBorder(BorderFactory.createEmptyBorder());
        jLabel_close.setBackground(new Color(0,0,0,0));
    }//GEN-LAST:event_jLabel_closeMouseReleased

    private void jLabel_insertMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_insertMouseEntered
        jLabel_insert.setBackground(new Color(51,204,0));
    }//GEN-LAST:event_jLabel_insertMouseEntered

    private void jLabel_insertMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_insertMouseExited
        jLabel_insert.setBackground(new Color(51,255,51));
    }//GEN-LAST:event_jLabel_insertMouseExited

    private void jLabel_insertMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_insertMousePressed
        jLabel_insert.setBackground(new Color(51,102,0));
    }//GEN-LAST:event_jLabel_insertMousePressed

    private void jLabel_insertMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_insertMouseReleased
        jLabel_insert.setBackground(new Color(51,255,51));
    }//GEN-LAST:event_jLabel_insertMouseReleased

    private void jLabel_updateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_updateMouseEntered
        jLabel_update.setBackground(new Color(0,204,204));
    }//GEN-LAST:event_jLabel_updateMouseEntered

    private void jLabel_updateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_updateMouseExited
        jLabel_update.setBackground(new Color(51,255,204));
    }//GEN-LAST:event_jLabel_updateMouseExited

    private void jLabel_updateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_updateMousePressed
        jLabel_update.setBackground(new Color(0,153,153));
    }//GEN-LAST:event_jLabel_updateMousePressed

    private void jLabel_updateMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_updateMouseReleased
        jLabel_update.setBackground(new Color(51,255,204));
    }//GEN-LAST:event_jLabel_updateMouseReleased

    private void jLabel_clearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_clearMouseEntered
        jLabel_clear.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_jLabel_clearMouseEntered

    private void jLabel_clearMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_clearMousePressed
        jLabel_clear.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_jLabel_clearMousePressed

    private void jLabel_clearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_clearMouseExited
        jLabel_clear.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_jLabel_clearMouseExited

    private void jLabel_clearMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_clearMouseReleased
        jLabel_clear.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_jLabel_clearMouseReleased

    private void jLabel_go_back_to_mainMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_go_back_to_mainMouseEntered
        jLabel_go_back_to_main.setBackground(new Color(153,102,0));
    }//GEN-LAST:event_jLabel_go_back_to_mainMouseEntered

    private void jLabel_go_back_to_mainMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_go_back_to_mainMouseExited
        jLabel_go_back_to_main.setBackground(new Color(204,153,0));
    }//GEN-LAST:event_jLabel_go_back_to_mainMouseExited

    private void jLabel_go_back_to_mainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_go_back_to_mainMousePressed
        jLabel_go_back_to_main.setBackground(new Color(102,51,0));
    }//GEN-LAST:event_jLabel_go_back_to_mainMousePressed

    private void jLabel_go_back_to_mainMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_go_back_to_mainMouseReleased
        jLabel_go_back_to_main.setBackground(new Color(204,153,0));
    }//GEN-LAST:event_jLabel_go_back_to_mainMouseReleased

    private void jLabel_deleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_deleteMouseEntered
        jLabel_delete.setBackground(new Color(255,51,0));
    }//GEN-LAST:event_jLabel_deleteMouseEntered

    private void jLabel_deleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_deleteMouseExited
        jLabel_delete.setBackground(new Color(255,102,0));
    }//GEN-LAST:event_jLabel_deleteMouseExited

    private void jLabel_deleteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_deleteMousePressed
        jLabel_delete.setBackground(new Color(204,51,0));
    }//GEN-LAST:event_jLabel_deleteMousePressed

    private void jLabel_deleteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_deleteMouseReleased
        jLabel_delete.setBackground(new Color(255,102,0));
    }//GEN-LAST:event_jLabel_deleteMouseReleased

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        String query = "";
    }//GEN-LAST:event_jLabel2MouseClicked

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
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AdminPanel().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_bg;
    private javax.swing.JLabel jLabel_cat;
    private javax.swing.JLabel jLabel_clear;
    private javax.swing.JLabel jLabel_close;
    private javax.swing.JLabel jLabel_delete;
    private javax.swing.JLabel jLabel_disc;
    private javax.swing.JLabel jLabel_go_back_to_main;
    private javax.swing.JLabel jLabel_insert;
    private javax.swing.JLabel jLabel_item;
    private javax.swing.JLabel jLabel_minimize;
    private javax.swing.JLabel jLabel_price;
    private javax.swing.JLabel jLabel_restxname;
    private javax.swing.JLabel jLabel_update;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_admin_data;
    private javax.swing.JTextField jTextField_cat;
    private javax.swing.JTextField jTextField_disc;
    private javax.swing.JTextField jTextField_item;
    private javax.swing.JTextField jTextField_price;
    private javax.swing.JTextField jTextField_qsearch;
    // End of variables declaration//GEN-END:variables
}
