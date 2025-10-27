/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package info5100.university.example.Ui.RegistrarRole;

import info5100.university.example.Context.UniversityContext;
import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseCatalog.CourseCatalog;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.Department.Department;
import info5100.university.example.Persona.Faculty.FacultyDirectory;
import info5100.university.example.Persona.Faculty.FacultyProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author agast
 */
public class ManageCourseOfferingJPanel extends javax.swing.JPanel {

    private final UniversityContext ctx;
private final Department dept;
private final CourseCatalog courseCatalog;
private final CourseSchedule activeSchedule;
private final javax.swing.JPanel CardSequencePanel;
private final info5100.university.example.Persona.Faculty.FacultyDirectory facultyDirectory;

    // simple side-car storage for room/time per offer (since model doesn’t have it)
    private final Map<CourseOffer, String> roomByOffer = new HashMap<>();
    private final Map<CourseOffer, String> timeByOffer = new HashMap<>();
    /**
     * Creates new form ManageCourseOfferingJPanel
     */
    public ManageCourseOfferingJPanel(UniversityContext ctx,
                                      FacultyDirectory facultyDirectory,
                                      JPanel cardSequencePanel) {
        initComponents();
        this.ctx = ctx;
    this.dept = ctx.getDepartment();
    this.courseCatalog = dept.getCourseCatalog();
    this.activeSchedule = ctx.getSchedule("Fall 2025"); // or from a cmbSemester
    this.facultyDirectory = dept.getFacultyDirectory(); // <-- same instance
    this.CardSequencePanel = cardSequencePanel;

        populateSemesters();
        populateCourses();
        populateFaculty();
        populateOffersTable();
        
    
    }
    
    

    private void populateSemesters() {
        cmbSemester.removeAllItems();
        for (String term : ctx.getAllSchedules().keySet()) {
            cmbSemester.addItem(term);
        }
    }

    private void populateCourses() {
        cmbCourse.removeAllItems();
        ArrayList<Course> list = courseCatalog.getCourseList();
        for (Course c : list) {
            // We only have getCOurseNumber() in your Course class
            cmbCourse.addItem(c.getCOurseNumber());
        }
    }

    private void populateFaculty() {
        cmbFaculty.removeAllItems();
        for (FacultyProfile fp : facultyDirectory.getFacultyList()) {
            String name = (fp.getPerson() != null) ? fp.getPerson().getName() : "Faculty";
            // store the ID in the display so we can re-find the profile if names duplicate
            String uid  = (fp.getPerson() != null) ? fp.getPerson().getUniversityId() : "";
            cmbFaculty.addItem(uid + " - " + name);
        }
    }

    private FacultyProfile findFacultyByComboSelection() {
        int idx = cmbFaculty.getSelectedIndex();
        if (idx < 0) return null;
        String sel = (String) cmbFaculty.getSelectedItem(); // "NU1001 - Prof Name"
        if (sel == null) return null;
        String uid = sel.split(" - ", 2)[0].trim();
        for (FacultyProfile fp : facultyDirectory.getFacultyList()) {
            if (fp.getPerson() != null &&
                fp.getPerson().getUniversityId() != null &&
                fp.getPerson().getUniversityId().equalsIgnoreCase(uid)) {
                return fp;
            }
        }
        return null;
    }

    private void populateOffersTable() {
        DefaultTableModel dtm = (DefaultTableModel) tblOffers.getModel();
        dtm.setRowCount(0);

        for (Map.Entry<String, CourseSchedule> e : ctx.getAllSchedules().entrySet()) {
            String term = e.getKey();
            CourseSchedule cs = e.getValue();
            if (cs == null) continue;

            for (CourseOffer co : cs.getCourseOffers()) {
                String cid = co.getCourseNumber();
                String cname = co.getCourseName();   // returns number in your model; still fills the column
                FacultyProfile fp = co.getFacultyProfile();
String faculty = "";
if (fp != null && fp.getPerson() != null && fp.getPerson().getName() != null) {
    faculty = fp.getPerson().getName();
}
                int capacity = (co.getSeatList() != null) ? co.getSeatList().size() : 0;
                int enrolled = co.getEnrolledCount();
                String room = roomByOffer.getOrDefault(co, "");
                String time = timeByOffer.getOrDefault(co, "");

                dtm.addRow(new Object[]{
                        term, cid, cname, faculty, capacity, enrolled, room, time
                });
            }
        }
    }
    
     private CourseOffer offerFromSelectedRow() {
        int row = tblOffers.getSelectedRow();
        if (row < 0) return null;

        String term = String.valueOf(tblOffers.getValueAt(row, 0));
        String cid  = String.valueOf(tblOffers.getValueAt(row, 1));
        CourseSchedule cs = ctx.getSchedule(term);
        if (cs == null) return null;
        return cs.getCourseOfferByNumber(cid);
    }

    private static int parseIntSafe(String s, int defVal) {
        try { return Integer.parseInt(s.trim()); } catch (Exception e) { return defVal; }
    }




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSemester = new javax.swing.JLabel();
        lblCourseID = new javax.swing.JLabel();
        lblFaculty = new javax.swing.JLabel();
        cmbSemester = new javax.swing.JComboBox<>();
        cmbCourse = new javax.swing.JComboBox<>();
        cmbFaculty = new javax.swing.JComboBox<>();
        lblCapacity = new javax.swing.JLabel();
        lblRoom = new javax.swing.JLabel();
        txtCapacity = new javax.swing.JTextField();
        txtRoom = new javax.swing.JTextField();
        txtTime = new javax.swing.JTextField();
        jScrollPaneOffers = new javax.swing.JScrollPane();
        tblOffers = new javax.swing.JTable();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnCreateOffer = new javax.swing.JButton();
        btnAssignFaculty = new javax.swing.JButton();
        lblTime = new javax.swing.JLabel();

        lblSemester.setText("Semester");

        lblCourseID.setText("Course ID");

        lblFaculty.setText("Faculty");

        cmbSemester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSemesterActionPerformed(evt);
            }
        });

        cmbCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbFaculty.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblCapacity.setText("Capacity");

        lblRoom.setText("Room");

        txtCapacity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapacityActionPerformed(evt);
            }
        });

        txtTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimeActionPerformed(evt);
            }
        });

        tblOffers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Semester", "Course ID", "Course Name", "Faculty", "Capacity", "Enrolled", "Room", "Time"
            }
        ));
        jScrollPaneOffers.setViewportView(tblOffers);

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnBack.setText("<<Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnCreateOffer.setText("Create offer");
        btnCreateOffer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateOfferActionPerformed(evt);
            }
        });

        btnAssignFaculty.setText("Assign Faculty");
        btnAssignFaculty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignFacultyActionPerformed(evt);
            }
        });

        lblTime.setText("Time");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSemester)
                    .addComponent(lblCourseID))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(94, 94, 94)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCapacity)
                    .addComponent(lblRoom))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(lblTime)
                        .addGap(64, 64, 64)
                        .addComponent(lblFaculty)
                        .addGap(18, 18, 18)
                        .addComponent(cmbFaculty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnBack)
                .addGap(34, 34, 34)
                .addComponent(btnAssignFaculty)
                .addGap(33, 33, 33)
                .addComponent(btnUpdate)
                .addGap(34, 34, 34)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 246, Short.MAX_VALUE)
                .addComponent(btnCreateOffer))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPaneOffers, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSemester)
                    .addComponent(cmbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCapacity)
                    .addComponent(txtCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTime)
                    .addComponent(lblFaculty)
                    .addComponent(cmbFaculty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCourseID)
                    .addComponent(lblRoom)
                    .addComponent(txtRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateOffer)
                    .addComponent(btnAssignFaculty)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnBack))
                .addGap(35, 35, 35))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(90, 90, 90)
                    .addComponent(jScrollPaneOffers, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(90, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbSemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSemesterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSemesterActionPerformed

    private void txtCapacityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapacityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapacityActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int row = tblOffers.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select an offering row first.");
            return;
        }
        String term = String.valueOf(tblOffers.getValueAt(row, 0));
        String cid  = String.valueOf(tblOffers.getValueAt(row, 1));
        CourseSchedule cs = ctx.getSchedule(term);
        if (cs == null) return;

        CourseOffer co = cs.getCourseOfferByNumber(cid);
        if (co == null) return;

        cs.getCourseOffers().remove(co);
        roomByOffer.remove(co);
        timeByOffer.remove(co);

        populateOffersTable();
        JOptionPane.showMessageDialog(this, "Offering deleted.");
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCreateOfferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateOfferActionPerformed
        // TODO add your handling code here:
        String term = (String) cmbSemester.getSelectedItem();
        String cid  = (String) cmbCourse.getSelectedItem();
        if (term == null || cid == null) {
            JOptionPane.showMessageDialog(this, "Select a semester and a course.");
            return;
        }

        CourseSchedule cs = ctx.getSchedule(term);
        if (cs == null) {
            JOptionPane.showMessageDialog(this, "Semester not found.");
            return;
        }

        CourseOffer co = cs.getCourseOfferByNumber(cid);
        if (co == null) {
            co = cs.newCourseOffer(cid);
        }
        if (co == null) {
            JOptionPane.showMessageDialog(this, "Could not create course offer (course not found in catalog?).");
            return;
        }

        int currentCap = (co.getSeatList() != null) ? co.getSeatList().size() : 0;
        int wantedCap  = parseIntSafe(txtCapacity.getText(), currentCap > 0 ? currentCap : 30);
        if (wantedCap > currentCap) {
            // increase by generating extra seats
            co.generatSeats(wantedCap - currentCap);
        }
        // room/time store locally
        roomByOffer.put(co, txtRoom.getText().trim());
        timeByOffer.put(co, txtTime.getText().trim());

        populateOffersTable();
        JOptionPane.showMessageDialog(this, "Course offering saved.");
    }//GEN-LAST:event_btnCreateOfferActionPerformed

    private void btnAssignFacultyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignFacultyActionPerformed
        // TODO add your handling code here:
        CourseOffer co = offerFromSelectedRow();
        if (co == null) {
            JOptionPane.showMessageDialog(this, "Select an offering row first.");
            return;
        }
        FacultyProfile fp = findFacultyByComboSelection();
        if (fp == null) {
            JOptionPane.showMessageDialog(this, "Select a faculty to assign.");
            return;
        }
        co.AssignAsTeacher(fp);  // your model’s method name
        populateOffersTable();
        JOptionPane.showMessageDialog(this, "Faculty assigned.");
    }//GEN-LAST:event_btnAssignFacultyActionPerformed

    private void txtTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimeActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        CourseOffer co = offerFromSelectedRow();
        if (co == null) {
            JOptionPane.showMessageDialog(this, "Select an offering row first.");
            return;
        }

        // First click: load existing values into fields (no listeners needed)
        boolean fieldsEmpty =
                txtCapacity.getText().trim().isEmpty() &&
                txtRoom.getText().trim().isEmpty() &&
                txtTime.getText().trim().isEmpty();

        if (fieldsEmpty) {
            int cap = (co.getSeatList() != null) ? co.getSeatList().size() : 0;
            txtCapacity.setText(String.valueOf(cap));
            txtRoom.setText(roomByOffer.getOrDefault(co, ""));
            txtTime.setText(timeByOffer.getOrDefault(co, ""));
            return;
        }

        // save back
        int currentCap = (co.getSeatList() != null) ? co.getSeatList().size() : 0;
        int wantedCap  = parseIntSafe(txtCapacity.getText(), currentCap);

        if (wantedCap > currentCap) {
            co.generatSeats(wantedCap - currentCap);
        } else if (wantedCap < currentCap) {
            // trim seats if they are NOT occupied; from the end
            ArrayList<info5100.university.example.CourseSchedule.Seat> seats = co.getSeatList();
            for (int i = seats.size() - 1; i >= 0 && seats.size() > wantedCap; i--) {
                if (!seats.get(i).isOccupied()) {
                    seats.remove(i);
                } else {
                    break; // stop if we hit an occupied seat
                }
            }
        }

        roomByOffer.put(co, txtRoom.getText().trim());
        timeByOffer.put(co, txtTime.getText().trim());

        populateOffersTable();
        JOptionPane.showMessageDialog(this, "Offering updated.");
        txtCapacity.setText("");
        txtRoom.setText("");
        txtTime.setText("");
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        CardSequencePanel.remove(this);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).previous(CardSequencePanel);
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAssignFaculty;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreateOffer;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbCourse;
    private javax.swing.JComboBox<String> cmbFaculty;
    private javax.swing.JComboBox<String> cmbSemester;
    private javax.swing.JScrollPane jScrollPaneOffers;
    private javax.swing.JLabel lblCapacity;
    private javax.swing.JLabel lblCourseID;
    private javax.swing.JLabel lblFaculty;
    private javax.swing.JLabel lblRoom;
    private javax.swing.JLabel lblSemester;
    private javax.swing.JLabel lblTime;
    private javax.swing.JTable tblOffers;
    private javax.swing.JTextField txtCapacity;
    private javax.swing.JTextField txtRoom;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
