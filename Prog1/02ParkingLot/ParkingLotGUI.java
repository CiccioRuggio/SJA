import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ParkingLotGUI extends JFrame {

    private static final int   TOTAL_PLACES = 10;
    private static final int   SLOT_W       = 140;
    private static final int   SLOT_H       = 110;
    private static final Color COLOR_FREE   = new Color(100, 200, 100);
    private static final Color COLOR_CAR    = new Color(220, 80,  80);
    private static final Color COLOR_TRUCK  = new Color(200, 140, 50);

    private final ParkingLot lot = new ParkingLot(TOTAL_PLACES);

    private final Map<Integer, Vehicle> slotToVehicle  = new HashMap<>();
    private final Map<String,  Vehicle> plateToVehicle = new HashMap<>();

    private JPanel[]  slotPanels;
    private JLabel[]  slotIconLabels;
    private JLabel[]  slotPlateLabels;

    private JLabel availableLabel;
    private JLabel isFullLabel;
    private JLabel revenueLabel;

    private JTextField        addPlateField;
    private JSpinner          positionSpinner;
    private JComboBox<String> typeCombo;
    private JLabel            lengthLabel;
    private JSpinner          lengthSpinner;
    private JTextField        exitPlateField;

    public ParkingLotGUI() {
        super("Parking Lot Manager");
        buildUI();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
        setVisible(true);

        new Timer(1000, e -> refreshInfo()).start();
    }

    // ─── UI construction ───────────────────────────────────────────────────────

    private void buildUI() {
        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(new EmptyBorder(12, 12, 12, 12));
        root.add(buildGridPanel(),    BorderLayout.CENTER);
        root.add(buildControlPanel(), BorderLayout.SOUTH);
        root.add(buildInfoPanel(),    BorderLayout.EAST);
        setContentPane(root);
    }

    // Ogni slot ha dimensione fissa: il GridLayout dimensiona il container di conseguenza
    private JPanel buildGridPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 5, 8, 8));
        panel.setBorder(new TitledBorder("Parking Slots"));

        slotPanels      = new JPanel[TOTAL_PLACES];
        slotIconLabels  = new JLabel[TOTAL_PLACES];
        slotPlateLabels = new JLabel[TOTAL_PLACES];

        for (int i = 0; i < TOTAL_PLACES; i++) {
            JPanel slot = new JPanel(new GridLayout(3, 1, 0, 2));
            slot.setPreferredSize(new Dimension(SLOT_W, SLOT_H));
            slot.setBackground(COLOR_FREE);
            slot.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));

            JLabel slotNum = new JLabel("Slot " + (i + 1), SwingConstants.CENTER);
            slotNum.setFont(slotNum.getFont().deriveFont(Font.BOLD, 12f));

            JLabel icon = new JLabel("", SwingConstants.CENTER);
            icon.setFont(icon.getFont().deriveFont(22f));

            JLabel plate = new JLabel("FREE", SwingConstants.CENTER);
            plate.setFont(plate.getFont().deriveFont(Font.PLAIN, 11f));

            slot.add(slotNum);
            slot.add(icon);
            slot.add(plate);

            slotPanels[i]      = slot;
            slotIconLabels[i]  = icon;
            slotPlateLabels[i] = plate;
            panel.add(slot);
        }
        return panel;
    }

    // GridBagLayout: layout a griglia stabile, niente FlowLayout che wrappa
    private JPanel buildControlPanel() {
        JPanel outer = new JPanel(new GridLayout(1, 2, 12, 0));
        outer.setBorder(new EmptyBorder(8, 0, 0, 0));

        outer.add(buildParkPanel());
        outer.add(buildExitPanel());
        return outer;
    }

    private JPanel buildParkPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Park a Vehicle"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets  = new Insets(5, 6, 5, 6);
        c.anchor  = GridBagConstraints.WEST;
        c.fill    = GridBagConstraints.HORIZONTAL;

        // riga 0 — Plate + Slot
        c.gridy = 0;
        c.gridx = 0; c.weightx = 0; panel.add(new JLabel("Plate:"), c);
        c.gridx = 1; c.weightx = 1;
        addPlateField = new JTextField(10);
        panel.add(addPlateField, c);

        c.gridx = 2; c.weightx = 0; panel.add(new JLabel("Slot:"), c);
        c.gridx = 3; c.weightx = 0;
        positionSpinner = new JSpinner(new SpinnerNumberModel(1, 1, TOTAL_PLACES, 1));
        positionSpinner.setPreferredSize(new Dimension(60, 26));
        panel.add(positionSpinner, c);

        // riga 1 — Type + Length
        c.gridy = 1;
        c.gridx = 0; c.weightx = 0; panel.add(new JLabel("Type:"), c);
        c.gridx = 1; c.weightx = 1;
        typeCombo = new JComboBox<>(new String[]{"Car", "Truck"});
        panel.add(typeCombo, c);

        c.gridx = 2; c.weightx = 0;
        lengthLabel = new JLabel("Length:");
        lengthLabel.setEnabled(false);
        panel.add(lengthLabel, c);

        c.gridx = 3; c.weightx = 0;
        lengthSpinner = new JSpinner(new SpinnerNumberModel(2, 1, 20, 1));
        lengthSpinner.setPreferredSize(new Dimension(60, 26));
        lengthSpinner.setEnabled(false);
        panel.add(lengthSpinner, c);

        typeCombo.addActionListener(e -> {
            boolean truck = "Truck".equals(typeCombo.getSelectedItem());
            lengthLabel.setEnabled(truck);
            lengthSpinner.setEnabled(truck);
        });

        // riga 2 — bottone Park
        c.gridy = 2; c.gridx = 0;
        c.gridwidth = 4;
        c.anchor = GridBagConstraints.CENTER;
        c.fill   = GridBagConstraints.NONE;
        JButton parkBtn = new JButton("Park");
        parkBtn.setPreferredSize(new Dimension(120, 30));
        parkBtn.addActionListener(e -> parkVehicle());
        panel.add(parkBtn, c);

        return panel;
    }

    private JPanel buildExitPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Exit a Vehicle"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 6, 5, 6);
        c.anchor = GridBagConstraints.WEST;
        c.fill   = GridBagConstraints.HORIZONTAL;

        // riga 0 — Plate
        c.gridy = 0;
        c.gridx = 0; c.weightx = 0; panel.add(new JLabel("Plate:"), c);
        c.gridx = 1; c.weightx = 1;
        exitPlateField = new JTextField(10);
        panel.add(exitPlateField, c);

        // riga 1 — bottone Exit
        c.gridy = 1; c.gridx = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.fill   = GridBagConstraints.NONE;
        JButton exitBtn = new JButton("Exit");
        exitBtn.setPreferredSize(new Dimension(120, 30));
        exitBtn.addActionListener(e -> exitVehicle());
        panel.add(exitBtn, c);

        return panel;
    }

    private JPanel buildInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new TitledBorder("Info"));
        panel.setPreferredSize(new Dimension(190, 0));

        availableLabel = new JLabel();
        isFullLabel    = new JLabel();
        revenueLabel   = new JLabel();

        Font f = availableLabel.getFont().deriveFont(13f);
        availableLabel.setFont(f);
        isFullLabel.setFont(f);
        revenueLabel.setFont(f);

        panel.add(Box.createVerticalStrut(14));
        panel.add(availableLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(isFullLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(revenueLabel);
        panel.add(Box.createVerticalGlue());

        refreshInfo();
        return panel;
    }

    // ─── Actions ───────────────────────────────────────────────────────────────

    private void parkVehicle() {
        String plate = addPlateField.getText().trim().toUpperCase();
        if (plate.isEmpty()) { error("Enter a plate number."); return; }
        if (plateToVehicle.containsKey(plate)) { error("Vehicle " + plate + " is already parked."); return; }
        if (lot.isFull()) { warn("The parking lot is full!"); return; }

        int position = (int) positionSpinner.getValue();
        if (slotToVehicle.containsKey(position)) { error("Slot " + position + " is already occupied."); return; }

        Vehicle vehicle = "Truck".equals(typeCombo.getSelectedItem())
                ? new Truck(plate, (int) lengthSpinner.getValue())
                : new Car(plate);

        if (lot.addVehicle(vehicle, position)) {
            slotToVehicle.put(position, vehicle);
            plateToVehicle.put(plate, vehicle);
            markOccupied(position, vehicle);
            addPlateField.setText("");
            refreshInfo();
        } else {
            error("Could not park at slot " + position + ".");
        }
    }

    private void exitVehicle() {
        String plate = exitPlateField.getText().trim().toUpperCase();
        if (plate.isEmpty()) { error("Enter a plate number."); return; }

        Vehicle vehicle = plateToVehicle.get(plate);
        if (vehicle == null) { error("No parked vehicle with plate: " + plate); return; }

        lot.exitCar(vehicle.getPlate());

        for (Map.Entry<Integer, Vehicle> e : slotToVehicle.entrySet()) {
            if (e.getValue() == vehicle) {
                markFree(e.getKey());
                slotToVehicle.remove(e.getKey());
                break;
            }
        }
        plateToVehicle.remove(plate);
        exitPlateField.setText("");
        refreshInfo();
    }

    // ─── Slot visuals ──────────────────────────────────────────────────────────

    private void markOccupied(int position, Vehicle vehicle) {
        int i = position - 1;
        boolean isTruck = vehicle instanceof Truck;

        slotPanels[i].setBackground(isTruck ? COLOR_TRUCK : COLOR_CAR);
        slotIconLabels[i].setText(isTruck ? "🚛" : "🚗");

        String info = vehicle.getPlate();
        if (isTruck) {
            Truck t = (Truck) vehicle;
            info += "  L:" + t.getLength() + " x" + String.format("%.1f", t.getPriceMultiplier());
        }
        slotPlateLabels[i].setText(info);
        slotPanels[i].revalidate();
        slotPanels[i].repaint();
    }

    private void markFree(int position) {
        int i = position - 1;
        slotPanels[i].setBackground(COLOR_FREE);
        slotIconLabels[i].setText("");
        slotPlateLabels[i].setText("FREE");
        slotPanels[i].revalidate();
        slotPanels[i].repaint();
    }

    // ─── Info refresh ──────────────────────────────────────────────────────────

    private void refreshInfo() {
        int available = lot.getAvailableSlots();
        availableLabel.setText("Available: " + available + " / " + TOTAL_PLACES);
        isFullLabel.setText("Full: " + (lot.isFull() ? "YES" : "no"));
        isFullLabel.setForeground(lot.isFull() ? Color.RED : new Color(0, 130, 0));
        revenueLabel.setText(String.format("Revenue: €%.4f", lot.getAmount()));
    }

    // ─── Helpers ───────────────────────────────────────────────────────────────

    private void error(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void warn(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ParkingLotGUI::new);
    }
}
