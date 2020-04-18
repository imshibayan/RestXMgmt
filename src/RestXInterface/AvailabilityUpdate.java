/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestXInterface;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

//Libraries to import for generate Graphics bill
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
/**
 *
 * @author shibayanmondal
 */
public class AvailabilityUpdate extends javax.swing.JFrame {

   
    int pX, pY;
    PreparedStatement p_st;
    ResultSet rs;
    
    DefaultTableModel menuTable, updatedTable;
    
    public AvailabilityUpdate() {
        initComponents();
        
        this.setSize(757, 482);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        itemComboBox();
        
        jTable_menu.setDefaultEditor(Object.class, null);
        
        //jTable_updated modification
        jTable_updated.setDefaultEditor(Object.class, null);

        showAvlTable();
    }
    
    
    public class Product {
        private String item;
//        private String price_of_item;
        
        Product(String item) {
            this.item = item;
//            this.price_of_item = price_of_item;
        }
        public String getItem() {
            return item;
        }
        public void setItem(String item) {
            this.item = item;
        }
//        public String getPrice() {
//            return price_of_item;
//        }
//        public void setPrice(String price) {
//            this.price_of_item = price;
//        }
    } 
    
    private void itemComboBox() {
       
         ArrayList<String> comboItem = new ArrayList<>();
//        String temp = "";
        String query = "SELECT `category` FROM `admin_data`";
        
        try {
            p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
            rs = p_st.executeQuery(query);
            while (rs.next()) {
                
                // Executing Count Query to count the number of category
//                temp = rs.getString("category");
//                String cnt_query = "SELECT COUNT(category) AS total FROM item_avail WHERE category = '" + 
//                temp + "'";
//                Statement p = DBConnect.DBConnect.getConnection().createStatement();
//                ResultSet rs1 = p.executeQuery(cnt_query);
//                rs1.next();
//                System.out.println(rs1.getInt("total"));
//                System.out.println(cnt_query);
                if (!comboItem.contains(rs.getString("category"))) {
                    comboItem.add(rs.getString("category"));
                    jComboBox_category.addItem(rs.getString("category"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerPortal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Product> getData(String category) {
        ArrayList<Product> myList = new ArrayList<>();
        String query = "SELECT `category`, `item_menu`, `price` FROM `admin_data`";
        try {
            p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
            rs = p_st.executeQuery();
            
            Product newP;
            while (rs.next()) {
                if (rs.getString("category").equals(category)) {
                    newP = new Product(rs.getString("item_menu"));
                    myList.add(newP);
                }                   
            } 
        } catch (SQLException ex) {
            Logger.getLogger(CustomerPortal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myList;
    }
    
    
    
    
    
    class AvlItem {
        private String cat, item, qty;
        AvlItem (String cat, String item, String qty) {
            this.cat = cat;
            this.item = item;
            this.qty = qty;
        }
        public String getCat() {
            return cat;
        }
        public void setCat(String cat) {
            this.cat = cat;
        }
        public String getItem() {
            return item;
        }
        public void setItem(String item) {
            this.item = item;
        }
        public String getQty() {
            return qty;
        }
        public void setQty (String qty) {
            this.qty = qty;
        }
      
    }
    
    private ArrayList<AvlItem> getDataAvl() {
        ArrayList<AvlItem> p = new ArrayList<>();
        String query = "SELECT `category` AS 'Category', `menu_item` AS 'Items', `quantity` AS 'QTY. Avl.' FROM `item_avail`";
        
        try {
            p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
            rs = p_st.executeQuery();
            
            AvlItem newA;
            while (rs.next()) {
                newA = new AvlItem(rs.getString("Category"), rs.getString("Items"), rs.getString("QTY. Avl."));
                p.add(newA);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    private void showAvlTable() {
        
        ArrayList<AvlItem> newA = getDataAvl();
        DefaultTableModel model = (DefaultTableModel)jTable_updated.getModel();
        Object[] row = new Object[3];
        for (int i = 0; i < newA.size(); i++) {
            row[0] = newA.get(i).getCat();
            row[1] = newA.get(i).getItem();
            row[2] = newA.get(i).getQty();
            model.addRow(row);
        }
        jTable_updated.setModel(model);
    }
    
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox_category = new javax.swing.JComboBox<>();
        jScrollPane_menu = new javax.swing.JScrollPane();
        jTable_menu = new javax.swing.JTable();
        jTextField_qty = new javax.swing.JTextField();
        jLabel_qty = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_updated = new javax.swing.JTable();
        jLabel_restxname = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel_update = new javax.swing.JLabel();
        jLabel_remove = new javax.swing.JLabel();
        jLabel_add_item = new javax.swing.JLabel();
        jLabel_go_to_main_menu = new javax.swing.JLabel();
        jLabel_close = new javax.swing.JLabel();
        jLabel_minimize = new javax.swing.JLabel();
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

        jComboBox_category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Category" }));
        jComboBox_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_categoryActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox_category);
        jComboBox_category.setBounds(40, 130, 260, 30);

        jTable_menu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Items"
            }
        ));
        jTable_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_menuMouseClicked(evt);
            }
        });
        jScrollPane_menu.setViewportView(jTable_menu);

        getContentPane().add(jScrollPane_menu);
        jScrollPane_menu.setBounds(40, 170, 260, 190);

        jTextField_qty.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        getContentPane().add(jTextField_qty);
        jTextField_qty.setBounds(170, 380, 130, 30);

        jLabel_qty.setFont(new java.awt.Font("Calibri", 1, 17)); // NOI18N
        jLabel_qty.setText("Quantity");
        getContentPane().add(jLabel_qty);
        jLabel_qty.setBounds(100, 380, 70, 28);

        jTable_updated.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Category", "Items", "QTY. Avl."
            }
        ));
        jTable_updated.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_updatedMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_updated);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(360, 130, 360, 230);

        jLabel_restxname.setBackground(new java.awt.Color(153, 255, 153));
        jLabel_restxname.setFont(new java.awt.Font("Tahoma", 1, 40)); // NOI18N
        jLabel_restxname.setForeground(new java.awt.Color(204, 0, 102));
        jLabel_restxname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_restxname.setText("Welcome to RestX");
        jLabel_restxname.setName(""); // NOI18N
        getContentPane().add(jLabel_restxname);
        jLabel_restxname.setBounds(90, 0, 560, 60);
        jLabel_restxname.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));

        jLabel1.setFont(new java.awt.Font("Sitka Text", 0, 21)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Choose from Main Menu");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 80, 300, 40);

        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 21)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Available Menu");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(370, 80, 310, 40);

        jLabel_update.setBackground(new java.awt.Color(0, 153, 255));
        jLabel_update.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
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
        jLabel_update.setBounds(590, 370, 130, 40);
        jLabel_update.setToolTipText("<html><p>This will add item from Main Menu</p><p>to Updated Avl. Menu with quantity</p></html>");

        jLabel_remove.setBackground(new java.awt.Color(255, 102, 102));
        jLabel_remove.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel_remove.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_remove.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_remove.setText("REMOVE ITEM");
        jLabel_remove.setOpaque(true);
        jLabel_remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_removeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_removeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_removeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_removeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_removeMouseReleased(evt);
            }
        });
        getContentPane().add(jLabel_remove);
        jLabel_remove.setBounds(450, 370, 120, 40);

        jLabel_add_item.setBackground(new java.awt.Color(0, 153, 255));
        jLabel_add_item.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel_add_item.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_add_item.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_add_item.setText("ADD WITH QTY");
        jLabel_add_item.setOpaque(true);
        jLabel_add_item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_add_itemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_add_itemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_add_itemMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_add_itemMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_add_itemMouseReleased(evt);
            }
        });
        getContentPane().add(jLabel_add_item);
        jLabel_add_item.setBounds(170, 420, 130, 40);

        jLabel_go_to_main_menu.setBackground(new java.awt.Color(204, 153, 0));
        jLabel_go_to_main_menu.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 14)); // NOI18N
        jLabel_go_to_main_menu.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_go_to_main_menu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_go_to_main_menu.setText("Go to Main Menu");
        jLabel_go_to_main_menu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_go_to_main_menu.setOpaque(true);
        jLabel_go_to_main_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_go_to_main_menuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_go_to_main_menuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_go_to_main_menuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_go_to_main_menuMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_go_to_main_menuMouseReleased(evt);
            }
        });
        getContentPane().add(jLabel_go_to_main_menu);
        jLabel_go_to_main_menu.setBounds(590, 420, 130, 40);

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
        jLabel_close.setBounds(710, 10, 40, 30);

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
        jLabel_minimize.setBounds(670, 10, 40, 30);

        jLabel_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/qty_upd_bg.jpg"))); // NOI18N
        getContentPane().add(jLabel_bg);
        jLabel_bg.setBounds(-220, -10, 980, 620);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_categoryActionPerformed
        ArrayList<Product> myList = getData(jComboBox_category.getSelectedItem().toString());
        menuTable = new DefaultTableModel();
        menuTable.setColumnIdentifiers(new Object[]{"Items"});
        Object[] row = new Object[1];
        //        System.out.println(myList.size());
        for (int i = 0; i < myList.size(); i++) {
            row[0] = myList.get(i).getItem();
//            row[1] = myList.get(i).getPrice();
            menuTable.addRow(row);
        }
        jTable_menu.setModel(menuTable);
    }//GEN-LAST:event_jComboBox_categoryActionPerformed

    private void jTable_menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_menuMouseClicked
        try {
            int i = jTable_menu.getSelectedRow();
            TableModel model = jTable_menu.getModel();
            String item = (model.getValueAt(i, 0).toString());

            String query = "SELECT `item_menu` FROM `admin_data`";
            p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
            rs = p_st.executeQuery();

            rs.next();
        
        } catch (SQLException ex) {
            Logger.getLogger(CustomerPortal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jTable_menuMouseClicked

    private void jLabel_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_updateMouseClicked

        try {
            int target_row = jTable_updated.getSelectedRow();
            String item = jTable_updated.getValueAt(target_row, 1).toString();
            String query_upd = "UPDATE `item_avail` SET `quantity` = ? WHERE `menu_item` = ?";
            
            p_st = DBConnect.DBConnect.getConnection().prepareStatement(query_upd);
            p_st.setString(1, jTextField_qty.getText());
            p_st.setString(2, item);
            p_st.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Updated Successfully.");
            
            // Refreshing the Admin Table after modification
                DefaultTableModel model = (DefaultTableModel)jTable_updated.getModel();
                model.setRowCount(0);
                showAvlTable();
            
            
            
            
            
            
            } catch (SQLException ex) {
            Logger.getLogger(AvailabilityUpdate.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Enter Valid Quantity.", "Quantity error!", 0);
        } catch (ArrayIndexOutOfBoundsException e) {
            Logger.getLogger(AvailabilityUpdate.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Please select an item.", "Selection error!", 0);
        } 
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
////////        try {
////////            String text_qty = jTextField_qty.getText();
////////            String query = "SELECT `category`, `item_menu` FROM `admin_data`";
////////            
////////            p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
////////            rs = p_st.executeQuery();
////////            
////////            // Row count of jTable_updated
////////            if (jTable_updated.getRowCount() == 0) {
////////                updatedTable = new DefaultTableModel();
////////                updatedTable.setColumnIdentifiers(new Object[]{"Category","Item","QTY. Avl."});
////////            }
////////            Object[] row = new Object[3];
////////            try {
////////                int i = jTable_menu.getSelectedRow();
////////                // Get quantity value from jTextField_qty
//////////            int qty;
//////////            qty = Integer.parseInt(text_qty);
////////
////////            if (text_qty.equals("") || !text_qty.matches("^[0-9]+$")) {
////////                JOptionPane.showMessageDialog(null, "Please enter valid quantity!", "Wrong Quantity", 1);
////////                jTextField_qty.setText("");
////////            } else {
////////                String cat = "";
////////                while (rs.next()) {
////////                    if (rs.getString("item_menu").equals(jTable_menu.getValueAt(i, 0).toString())) {
////////                        cat = rs.getString("category");
////////                    }
////////                }
////////
////////                        // 1st row is category
////////                        row[0] = cat;
////////
////////                        // 2nd row is item name
////////                        row[1] = jTable_menu.getValueAt(i, 0).toString();
////////                        
////////// 3rd row is net quantity
////////                        row[2] = text_qty;
////////                        // 3rd row is total amount calculation
////////
////////                        updatedTable.addRow(row);
////////            }
////////            jTable_updated.setModel(updatedTable);
////////            jTextField_qty.setText("");
////////
////////            // Table modification
////////            TableRowSorter<TableModel> sorter2 = new TableRowSorter<>(jTable_updated.getModel());
////////            jTable_updated.setRowSorter(sorter2);
////////            ArrayList<RowSorter.SortKey> sortKeys2 = new ArrayList<>();
////////
////////            int columnIndexToSort2 = 0;
////////            sortKeys2.add(new RowSorter.SortKey(columnIndexToSort2, SortOrder.ASCENDING));
////////
////////            sorter2.setSortKeys(sortKeys2);
////////            sorter2.sort();
////////
////////
////////
////////            } catch(ArrayIndexOutOfBoundsException e) {
////////                System.err.println("ArrayIndexOutOfBoundsException: " + e.toString());
////////                JOptionPane.showMessageDialog(null, "Select an item.", "Selection error!", 1);
////////                jTextField_qty.setText("");
////////            } catch(NumberFormatException c) {
////////                System.err.println("NumberFormatException: " + c.toString());
////////                JOptionPane.showMessageDialog(null, "Enter Valid Quantity.", "Invalid Quantity!", 1);
////////                jTextField_qty.setText("");
////////            }
////////        } catch(SQLException ex) {
////////            Logger.getLogger(AvailabilityUpdate.class.getName()).log(Level.SEVERE, null, ex);
////////        }
        
    }//GEN-LAST:event_jLabel_updateMouseClicked

    private void jLabel_removeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_removeMouseClicked
        
        try {
            int i = jTable_updated.getSelectedRow();
            String query = "DELETE FROM `item_avail` WHERE `menu_item` = '" + jTable_updated.getValueAt(i, 1).toString() + "'";
            
            try {
                p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
                int suc = p_st.executeUpdate();
                if (suc > 0) {
                    JOptionPane.showMessageDialog(null, "Deletion successful.", "Success",1);
                }
                // Refreshing the Admin Table after modification
                DefaultTableModel model = (DefaultTableModel)jTable_updated.getModel();
                model.setRowCount(0);
                showAvlTable();
                
            } catch (SQLException ex) {
                Logger.getLogger(AvailabilityUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
//            int avail_quantity, no_of_rows_avail, target_row;
//            no_of_rows_avail = jTable_avl_itm.getRowCount();
            // Searching availability table to match the item
//            for (target_row = 0; target_row < no_of_rows_avail; target_row++) {
//                if (jTable_avl_itm.getValueAt(target_row, 1).toString().equals(jTable_selected_item.getValueAt(i, 0).toString())) {
//                    break;
//                }
//            }
//            avail_quantity = Integer.parseInt(jTable_avl_itm.getValueAt(target_row, 2).toString());
//            if (i>-1) {
//                avail_quantity = avail_quantity + qty;
//                jTable_avl_itm.setValueAt(avail_quantity, target_row, 2);
//
//                ((DefaultTableModel)jTable_updated.getModel()).removeRow(i);
//
//            } else {
//                JOptionPane.showMessageDialog(null,"Please select a row!" ,"Invalid Selection!",1);
//            }
//            int row_number = jTable_selected_item.getRowCount();
//            float total_amount_of_all_items = 0;
//            for (int j = 0; j < row_number; j++) {
//                total_amount_of_all_items += Float.parseFloat(jTable_selected_item.getValueAt(j, 2).toString());
//            }
//            jTextField_total.setText(Float.toString(total_amount_of_all_items));
        } catch(ArrayIndexOutOfBoundsException e) {
            System.err.println("ArrayIndexOutOfBoundsException: " + e.toString());
            JOptionPane.showMessageDialog(null, "Select an item.", "Selection error!", 1);
        } catch(NumberFormatException c) {
            System.err.println("NumberFormatException: " + c.toString());
            JOptionPane.showMessageDialog(null, "Select an item.", "Selection error!", 1);
        }
    }//GEN-LAST:event_jLabel_removeMouseClicked

    private void jLabel_add_itemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_add_itemMouseClicked
        try {
            String cat = "";
            PreparedStatement p_st_admin, p_st_avl;
            ResultSet rs_admin, rs_avl;
            
            int success = 0;
            ArrayList<String> get_items = new ArrayList<>();
            PreparedStatement p_st_temp;
            
//            int row_no_upd_table = jTable_updated.getRowCount();
            String query_fetchdata_admin = "SELECT * FROM `admin_data`";
            String query_fetchdata_avl = "SELECT * FROM `item_avail`";
            
//            String query_upd = "";
            
            p_st_admin = DBConnect.DBConnect.getConnection().prepareStatement(query_fetchdata_admin);
            rs_admin = p_st_admin.executeQuery();
            
            p_st_avl = DBConnect.DBConnect.getConnection().prepareStatement(query_fetchdata_avl);
            rs_avl = p_st_avl.executeQuery();
            
            int sel_row = jTable_menu.getSelectedRow();
            String item = jTable_menu.getValueAt(sel_row, 0).toString();
            String qty = jTextField_qty.getText();
            
            // Adding all the available items to get_items ArrayList
            while (rs_avl.next()) {
                get_items.add(rs_avl.getString("menu_item"));
            }
            
            if (!get_items.contains(item)) {
                    
                
                while (rs_admin.next()) {
                    if (rs_admin.getString("item_menu").equals(item)) {
                        cat = rs_admin.getString("category");
                    }
                }    
                    
                String query_insert = "INSERT INTO `item_avail` (`category`, "
                            + "`menu_item`, `quantity`) VALUES ('" + cat 
                            + "', '" + item + "', '" + qty + "')";
                    p_st_temp = DBConnect.DBConnect.getConnection().prepareStatement(query_insert);
                    success = p_st_temp.executeUpdate();
                } else {
                
                    String query_upd = "UPDATE `item_avail` SET `quantity` = ? WHERE `menu_item` = ?";
                    p_st_temp =DBConnect.DBConnect.getConnection().prepareStatement(query_upd);
                    p_st_temp.setString(1, qty);
                    p_st_temp.setString(2, item);
                    success = p_st_temp.executeUpdate();
                }
            
            if (success > 0) {
                    JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
                }
                //((DefaultTableModel)jTable_updated.getModel()).setRowCount(0);
              else {
                JOptionPane.showMessageDialog(null, "No item selected!", "Selection Error!", 2);
            }
            
            
             // Refreshing the Admin Table after modification
                DefaultTableModel model = (DefaultTableModel)jTable_updated.getModel();
                model.setRowCount(0);
                showAvlTable();
            
            
            
            
//            if (row_no_upd_table != 0 ) {
//                for (int i = 0; i < row_no_upd_table; i++) {
//                    int flag = 0;
//                    String cat = jTable_updated.getValueAt(i, 0).toString();
//                    String item = jTable_updated.getValueAt(i, 1).toString();
//                    String qty = jTable_updated.getValueAt(i, 2).toString();
                    
//                    String query_insert = "INSERT INTO `item_avail` (`category`, "
//                            + "`menu_item`, `quantity`) VALUES ('" + cat 
//                            + "', '" + item + "', '" + qty + "')";
//                    
//                    String query_upd = "UPDATE `item_avail` SET `quantity` = ? WHERE `menu_item` = ?";
                    
//                    if (!get_items.contains(item)) {
//                        p_st_temp = DBConnect.DBConnect.getConnection().prepareStatement(query_insert);
//                        success = p_st_temp.executeUpdate();
//                    } else {
//                        p_st_temp =DBConnect.DBConnect.getConnection().prepareStatement(query_upd);
//                        p_st_temp.setString(1, qty);
//                        p_st_temp.setString(2, item);
//                        success = p_st_temp.executeUpdate();
//                    }
                    // Starting from first element of item_avail table resultset
                    
//                }
//                if (success > 0) {
//                    JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
//                }
//                ((DefaultTableModel)jTable_updated.getModel()).setRowCount(0);
//            } else {
//                JOptionPane.showMessageDialog(null, "No item selected!", "Selection Error!", 2);
//            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AvailabilityUpdate.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Enter Valid Quantity.", "Quantity error!", 0);
        } catch (ArrayIndexOutOfBoundsException e) {
            Logger.getLogger(AvailabilityUpdate.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Please select an item.", "Selection error!", 0);
        } 
    }//GEN-LAST:event_jLabel_add_itemMouseClicked

    private void jLabel_go_to_main_menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_go_to_main_menuMouseClicked
        new RestXInterface.FirstWelcomeScrn().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel_go_to_main_menuMouseClicked

    private void jLabel_go_to_main_menuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_go_to_main_menuMouseEntered
        jLabel_go_to_main_menu.setBackground(new Color(153,102,0));
    }//GEN-LAST:event_jLabel_go_to_main_menuMouseEntered

    private void jLabel_go_to_main_menuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_go_to_main_menuMouseExited
        jLabel_go_to_main_menu.setBackground(new Color(204,153,0));
    }//GEN-LAST:event_jLabel_go_to_main_menuMouseExited

    private void jLabel_go_to_main_menuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_go_to_main_menuMousePressed
         jLabel_go_to_main_menu.setBackground(new Color(102,51,0));
    }//GEN-LAST:event_jLabel_go_to_main_menuMousePressed

    private void jLabel_go_to_main_menuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_go_to_main_menuMouseReleased
        jLabel_go_to_main_menu.setBackground(new Color(204,153,0));
    }//GEN-LAST:event_jLabel_go_to_main_menuMouseReleased

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        pX = evt.getX();
        pY = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - pX, 
                this.getLocation().y + evt.getY() - pY);
    }//GEN-LAST:event_formMouseDragged

    private void jTable_updatedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_updatedMouseClicked
        jTextField_qty.setText(jTable_updated.getValueAt(jTable_updated.getSelectedRow(), 2).toString());

    }//GEN-LAST:event_jTable_updatedMouseClicked

    private void jLabel_add_itemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_add_itemMouseEntered
        jLabel_add_item.setBackground(new Color(0,102,255));
    }//GEN-LAST:event_jLabel_add_itemMouseEntered

    private void jLabel_add_itemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_add_itemMouseExited
        jLabel_add_item.setBackground(new Color(0,153,255));
    }//GEN-LAST:event_jLabel_add_itemMouseExited

    private void jLabel_add_itemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_add_itemMousePressed
        jLabel_add_item.setBackground(new Color(0,51,204));
    }//GEN-LAST:event_jLabel_add_itemMousePressed

    private void jLabel_add_itemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_add_itemMouseReleased
        jLabel_add_item.setBackground(new Color(0,102,255));
    }//GEN-LAST:event_jLabel_add_itemMouseReleased

    private void jLabel_removeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_removeMouseEntered
        jLabel_remove.setBackground(new Color(255,51,51));
    }//GEN-LAST:event_jLabel_removeMouseEntered

    private void jLabel_removeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_removeMouseExited
        jLabel_remove.setBackground(new Color(255,102,102));
    }//GEN-LAST:event_jLabel_removeMouseExited

    private void jLabel_removeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_removeMousePressed
        jLabel_remove.setBackground(new Color(204,0,51));
    }//GEN-LAST:event_jLabel_removeMousePressed

    private void jLabel_removeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_removeMouseReleased
         jLabel_remove.setBackground(new Color(255,102,102));
    }//GEN-LAST:event_jLabel_removeMouseReleased

    private void jLabel_updateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_updateMouseEntered
        jLabel_update.setBackground(new Color(0,102,255));
    }//GEN-LAST:event_jLabel_updateMouseEntered

    private void jLabel_updateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_updateMouseExited
        jLabel_update.setBackground(new Color(0,153,255));
    }//GEN-LAST:event_jLabel_updateMouseExited

    private void jLabel_updateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_updateMousePressed
        jLabel_update.setBackground(new Color(0,51,204));
    }//GEN-LAST:event_jLabel_updateMousePressed

    private void jLabel_updateMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_updateMouseReleased
        jLabel_update.setBackground(new Color(0,153,255));
    }//GEN-LAST:event_jLabel_updateMouseReleased

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
            java.util.logging.Logger.getLogger(AvailabilityUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AvailabilityUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AvailabilityUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AvailabilityUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AvailabilityUpdate().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox_category;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_add_item;
    private javax.swing.JLabel jLabel_bg;
    private javax.swing.JLabel jLabel_close;
    private javax.swing.JLabel jLabel_go_to_main_menu;
    private javax.swing.JLabel jLabel_minimize;
    private javax.swing.JLabel jLabel_qty;
    private javax.swing.JLabel jLabel_remove;
    private javax.swing.JLabel jLabel_restxname;
    private javax.swing.JLabel jLabel_update;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane_menu;
    private javax.swing.JTable jTable_menu;
    private javax.swing.JTable jTable_updated;
    private javax.swing.JTextField jTextField_qty;
    // End of variables declaration//GEN-END:variables
}
