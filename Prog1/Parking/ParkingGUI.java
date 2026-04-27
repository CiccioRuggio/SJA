import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class ParkingGUI extends JFrame {

    private static final int DEFAULT_SIZE = 10;

    private Parking parking;
    private int parkingSize;

    // panels
    private JPanel slotsPanel;
    private JPanel centerWrapper;
    private JLabel[] slotLabels;

    // header
    private JLabel titleLabel;

    // stats labels
    private JLabel lblFree;
    private JLabel lblOccupied;
    private JLabel lblFull;

    // input fields
    private JTextField tfAddPlate;
    private JSpinner spAddSlot;
    private JSpinner spRemoveSlot;
    private JSpinner spResizeSize;
    private JTextField tfCheckPlate;

    // log
    private JTextArea logArea;

    // colors
    private static final Color FREE_COLOR     = new Color(72, 199, 142);
    private static final Color OCCUPIED_COLOR = new Color(255, 100, 100);
    private static final Color BG_COLOR       = new Color(30, 30, 46);
    private static final Color PANEL_COLOR    = new Color(45, 45, 62);
    private static final Color ACCENT_COLOR   = new Color(137, 180, 250);
    private static final Color TEXT_COLOR     = new Color(220, 220, 220);
    private static final Color BTN_COLOR      = new Color(80, 100, 180);

    public ParkingGUI() {
        this.parkingSize = DEFAULT_SIZE;
        this.parking = new Parking(parkingSize);
        buildUI();
        refreshAll();
    }

    private void buildUI() {
        setTitle("Parking Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(BG_COLOR);
        setLayout(new BorderLayout(10, 10));
        getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        getContentPane().setBackground(BG_COLOR);

        add(buildHeader(),   BorderLayout.NORTH);
        add(buildCenter(),   BorderLayout.CENTER);
        add(buildControls(), BorderLayout.EAST);
        add(buildLog(),      BorderLayout.SOUTH);

        pack();
        setMinimumSize(new Dimension(820, 600));
        setLocationRelativeTo(null);
    }

    private JPanel buildHeader() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(BG_COLOR);
        p.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        titleLabel = new JLabel("Parking Manager  —  " + parkingSize + " slots", SwingConstants.LEFT);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(ACCENT_COLOR);

        JPanel stats = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        stats.setBackground(BG_COLOR);
        lblFree     = styledStatLabel("Free: 0");
        lblOccupied = styledStatLabel("Occupied: 0");
        lblFull     = styledStatLabel("Full: —");
        stats.add(lblFree);
        stats.add(lblOccupied);
        stats.add(lblFull);

        p.add(titleLabel, BorderLayout.WEST);
        p.add(stats,      BorderLayout.EAST);
        return p;
    }

    private JLabel styledStatLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.BOLD, 14));
        l.setForeground(TEXT_COLOR);
        return l;
    }

    // returns the wrapper; also stores ref in centerWrapper for later rebuilding
    private JPanel buildCenter() {
        centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setBackground(BG_COLOR);

        JLabel sub = new JLabel("Parking slots");
        sub.setFont(new Font("SansSerif", Font.BOLD, 13));
        sub.setForeground(new Color(150, 150, 170));
        sub.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        centerWrapper.add(sub, BorderLayout.NORTH);

        rebuildSlotsPanel();
        return centerWrapper;
    }

    private void rebuildSlotsPanel() {
        if (slotsPanel != null) centerWrapper.remove(slotsPanel);

        int cols = Math.min(5, parkingSize);
        int rows = (int) Math.ceil((double) parkingSize / cols);
        slotsPanel = new JPanel(new GridLayout(rows, cols, 8, 8));
        slotsPanel.setBackground(BG_COLOR);
        slotLabels = new JLabel[parkingSize];

        for (int i = 0; i < parkingSize; i++) {
            slotLabels[i] = buildSlotLabel(i);
            slotsPanel.add(slotLabels[i]);
        }

        centerWrapper.add(slotsPanel, BorderLayout.CENTER);
    }

    private JLabel buildSlotLabel(int index) {
        JLabel l = new JLabel("<html><center>#" + index + "<br>FREE</center></html>", SwingConstants.CENTER);
        l.setPreferredSize(new Dimension(110, 70));
        l.setFont(new Font("Monospaced", Font.BOLD, 12));
        l.setOpaque(true);
        l.setBackground(FREE_COLOR);
        l.setForeground(Color.WHITE);
        l.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 40), 1, true),
            BorderFactory.createEmptyBorder(4, 4, 4, 4)
        ));
        return l;
    }

    private JPanel buildControls() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(BG_COLOR);
        p.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 0));
        p.setPreferredSize(new Dimension(230, 0));

        p.add(buildAddPanel());
        p.add(Box.createVerticalStrut(10));
        p.add(buildRemovePanel());
        p.add(Box.createVerticalStrut(10));
        p.add(buildCheckPanel());
        p.add(Box.createVerticalStrut(10));
        p.add(buildResizePanel());
        return p;
    }

    private JPanel buildAddPanel() {
        JPanel p = card("Add Car");

        tfAddPlate = styledTextField("Plate (e.g. AB123CD)");
        spAddSlot  = new JSpinner(new SpinnerNumberModel(0, 0, parkingSize - 1, 1));
        styleSpinner(spAddSlot);

        JButton btn = styledButton("Add", new Color(72, 160, 100));
        btn.addActionListener(e -> doAddCar());

        p.add(labeledRow("Plate:", tfAddPlate));
        p.add(Box.createVerticalStrut(6));
        p.add(labeledRow("Slot: ", spAddSlot));
        p.add(Box.createVerticalStrut(8));
        p.add(btn);
        return p;
    }

    private JPanel buildRemovePanel() {
        JPanel p = card("Remove Car");

        spRemoveSlot = new JSpinner(new SpinnerNumberModel(0, 0, parkingSize - 1, 1));
        styleSpinner(spRemoveSlot);

        JButton btn = styledButton("Remove", new Color(200, 80, 80));
        btn.addActionListener(e -> doRemoveCar());

        p.add(labeledRow("Slot: ", spRemoveSlot));
        p.add(Box.createVerticalStrut(8));
        p.add(btn);
        return p;
    }

    private JPanel buildCheckPanel() {
        JPanel p = card("Check Plate");

        tfCheckPlate = styledTextField("Plate to search");

        JButton btn = styledButton("Check", BTN_COLOR);
        btn.addActionListener(e -> doCheckPlate());

        p.add(labeledRow("Plate:", tfCheckPlate));
        p.add(Box.createVerticalStrut(8));
        p.add(btn);
        return p;
    }

    private JPanel buildResizePanel() {
        JPanel p = card("Resize Parking");

        spResizeSize = new JSpinner(new SpinnerNumberModel(parkingSize, 1, 50, 1));
        styleSpinner(spResizeSize);

        JButton btn = styledButton("Apply", new Color(160, 110, 60));
        btn.addActionListener(e -> doResize());

        p.add(labeledRow("Size: ", spResizeSize));
        p.add(Box.createVerticalStrut(8));
        p.add(btn);
        return p;
    }

    private JPanel buildLog() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(BG_COLOR);
        p.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel lbl = new JLabel("Log");
        lbl.setFont(new Font("SansSerif", Font.BOLD, 13));
        lbl.setForeground(new Color(150, 150, 170));
        lbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 4, 0));

        logArea = new JTextArea(4, 0);
        logArea.setEditable(false);
        logArea.setBackground(new Color(20, 20, 33));
        logArea.setForeground(new Color(180, 220, 180));
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        logArea.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));

        JScrollPane scroll = new JScrollPane(logArea);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 90)));

        p.add(lbl,    BorderLayout.NORTH);
        p.add(scroll, BorderLayout.CENTER);
        return p;
    }

    // --- actions ---

    private void doAddCar() {
        String plate = tfAddPlate.getText().trim().toUpperCase();
        int slot = (int) spAddSlot.getValue();
        if (plate.isEmpty()) { log("ERROR: plate is empty."); return; }
        Car car = new Car(plate);
        if (parking.checkPlate(car)) {
            log("ERROR: plate " + plate + " already in parking.");
            return;
        }
        parking.addCar(car, slot);
        log("Added " + plate + " → slot #" + slot);
        tfAddPlate.setText("");
        refreshAll();
    }

    private void doRemoveCar() {
        int slot = (int) spRemoveSlot.getValue();
        parking.removeCar(slot);
        log("Removed car from slot #" + slot);
        refreshAll();
    }

    private void doCheckPlate() {
        String plate = tfCheckPlate.getText().trim().toUpperCase();
        if (plate.isEmpty()) { log("ERROR: plate is empty."); return; }
        Car car = new Car(plate);
        boolean found = parking.checkPlate(car);
        log("checkPlate(\"" + plate + "\") → " + (found ? "FOUND" : "NOT FOUND"));
        tfCheckPlate.setText("");
    }

    private void doResize() {
        int newSize = (int) spResizeSize.getValue();
        parking = new Parking(newSize);
        parkingSize = newSize;

        // update spinner bounds
        spAddSlot.setModel(new SpinnerNumberModel(0, 0, newSize - 1, 1));
        spRemoveSlot.setModel(new SpinnerNumberModel(0, 0, newSize - 1, 1));

        // rebuild slot grid
        rebuildSlotsPanel();
        centerWrapper.revalidate();
        centerWrapper.repaint();

        titleLabel.setText("Parking Manager  —  " + newSize + " slots");
        log("Parking resized to " + newSize + " slots (reset).");
        refreshAll();
        pack();
    }

    // --- refresh ---

    private void refreshAll() {
        refreshSlots();
        refreshStats();
    }

    private void refreshSlots() {
        for (int i = 0; i < parkingSize; i++) {
            Car probe = new Car("__PROBE__" + i);
            parking.addCar(probe, i);
            boolean wasOccupied = !parking.checkPlate(probe);
            if (!wasOccupied) parking.removeCar(i);
            if (wasOccupied) {
                slotLabels[i].setBackground(OCCUPIED_COLOR);
                slotLabels[i].setText("<html><center>#" + i + "<br>OCCUPIED</center></html>");
            } else {
                slotLabels[i].setBackground(FREE_COLOR);
                slotLabels[i].setText("<html><center>#" + i + "<br>FREE</center></html>");
            }
        }
        slotsPanel.repaint();
    }

    private void refreshStats() {
        int free = parking.freePlaces();
        int occ  = parking.occupatedPlaces();
        boolean full = parking.isFull();
        lblFree.setText("Free: " + free);
        lblOccupied.setText("Occupied: " + occ);
        lblFull.setText(full ? "FULL" : "Not full");
        lblFull.setForeground(full ? OCCUPIED_COLOR : new Color(150, 220, 150));
    }

    private void log(String msg) {
        logArea.append(msg + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    // --- helpers ---

    private JPanel card(String title) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(PANEL_COLOR);
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 110)),
                title,
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12),
                ACCENT_COLOR
            ),
            BorderFactory.createEmptyBorder(6, 8, 8, 8)
        ));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        return p;
    }

    private JTextField styledTextField(String placeholder) {
        JTextField tf = new JTextField(12);
        tf.setBackground(new Color(55, 55, 75));
        tf.setForeground(TEXT_COLOR);
        tf.setCaretColor(TEXT_COLOR);
        tf.setFont(new Font("Monospaced", Font.PLAIN, 13));
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(90, 90, 120)),
            BorderFactory.createEmptyBorder(3, 6, 3, 6)
        ));
        tf.putClientProperty("JTextField.placeholderText", placeholder);
        return tf;
    }

    private void styleSpinner(JSpinner s) {
        s.setBackground(new Color(55, 55, 75));
        s.setForeground(TEXT_COLOR);
        s.setFont(new Font("Monospaced", Font.PLAIN, 13));
        ((JSpinner.DefaultEditor) s.getEditor()).getTextField().setBackground(new Color(55, 55, 75));
        ((JSpinner.DefaultEditor) s.getEditor()).getTextField().setForeground(TEXT_COLOR);
    }

    private JButton styledButton(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(7, 16, 7, 16));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        return b;
    }

    private JPanel labeledRow(String label, JComponent field) {
        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setBackground(PANEL_COLOR);
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lbl.setForeground(TEXT_COLOR);
        lbl.setPreferredSize(new Dimension(42, 0));
        row.add(lbl,   BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        return row;
    }

    // --- entry point ---

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ParkingGUI().setVisible(true));
    }
}
