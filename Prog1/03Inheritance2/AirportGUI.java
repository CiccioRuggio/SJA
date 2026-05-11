import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.*;
import java.util.Locale;
import javax.swing.*;

public class AirportGUI extends JFrame {

    private static final Color PRIMARY    = new Color(37,  99,  235);
    private static final Color PRIMARY_DK = new Color(29,  78,  216);
    private static final Color LIGHT_BG   = new Color(241, 245, 249);
    private static final Color SEL_BG     = new Color(219, 234, 254);
    private static final Color MUTED      = new Color(100, 116, 139);
    private static final Color BORDER_COL = new Color(203, 213, 225);
    private static final Color RED        = new Color(220, 38,  38);
    private static final Color TEXT_DARK  = new Color(15,  23,  42);
    private static final DateTimeFormatter DISPLAY_FMT =
            DateTimeFormatter.ofPattern("dd MMM yyyy  HH:mm");

    private final Airport airport = new Airport();
    private final DefaultListModel<Flight> listModel = new DefaultListModel<>();
    private final JList<Flight> flightList  = new JList<>(listModel);
    private final JPanel       detailsPanel = new JPanel();

    public AirportGUI() {
        setTitle("Airport Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(940, 530);
        setLocationRelativeTo(null);
        buildUI();
        setVisible(true);
    }

    // ── main window ──────────────────────────────────────────────────────────

    private void buildUI() {
        // header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(PRIMARY);
        header.setBorder(BorderFactory.createEmptyBorder(13, 20, 13, 20));
        JLabel titleLbl = new JLabel("✈   Airport Manager");
        titleLbl.setFont(new Font("SansSerif", Font.BOLD, 21));
        titleLbl.setForeground(Color.WHITE);
        header.add(titleLbl, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        // left panel
        flightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        flightList.setCellRenderer(new FlightCellRenderer());
        flightList.setBackground(Color.WHITE);
        flightList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) showDetails();
        });

        JLabel listHeader = sectionLabel("  FLIGHTS");
        JScrollPane listScroll = new JScrollPane(flightList);
        listScroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, BORDER_COL));

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(265, 0));
        leftPanel.add(listHeader, BorderLayout.NORTH);
        leftPanel.add(listScroll, BorderLayout.CENTER);

        // right panel
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(LIGHT_BG);
        showPlaceholder();

        JLabel detailsHeader = sectionLabel("  DETAILS");
        JScrollPane detailsScroll = new JScrollPane(detailsPanel);
        detailsScroll.setBorder(null);
        detailsScroll.getViewport().setBackground(LIGHT_BG);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(detailsHeader, BorderLayout.NORTH);
        rightPanel.add(detailsScroll, BorderLayout.CENTER);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        split.setDividerLocation(265);
        split.setDividerSize(1);
        split.setBorder(null);
        add(split, BorderLayout.CENTER);

        // toolbar
        JButton addBtn    = styledBtn("＋  Add Flight", PRIMARY);
        JButton removeBtn = styledBtn("✕  Remove", RED);
        addBtn.addActionListener(e -> showAddDialog());
        removeBtn.addActionListener(e -> removeFlight());

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        toolbar.setBackground(LIGHT_BG);
        toolbar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_COL));
        toolbar.add(removeBtn);
        toolbar.add(addBtn);
        add(toolbar, BorderLayout.SOUTH);
    }

    private JLabel sectionLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 11));
        lbl.setForeground(MUTED);
        lbl.setBackground(LIGHT_BG);
        lbl.setOpaque(true);
        lbl.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COL),
            BorderFactory.createEmptyBorder(6, 10, 6, 0)));
        return lbl;
    }

    private JButton styledBtn(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(7, 18, 7, 18));
        return btn;
    }

    // ── flight list renderer ──────────────────────────────────────────────────

    private static class FlightCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            Flight f = (Flight) value;
            Plane  p = f.getPlane();
            String icon = (p instanceof CargoPlane) ? "📦" : "🛫";
            String type = (p instanceof CargoPlane) ? "Cargo" : "Airline";

            JPanel cell = new JPanel();
            cell.setLayout(new BoxLayout(cell, BoxLayout.Y_AXIS));
            cell.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

            JLabel route = new JLabel(icon + "  " + f.getDeparture() + " → " + f.getArrival());
            route.setFont(new Font("SansSerif", Font.BOLD, 13));

            JLabel sub = new JLabel("     " + p.getName() + "  ·  " + type);
            sub.setFont(new Font("SansSerif", Font.PLAIN, 11));
            sub.setForeground(MUTED);

            if (isSelected) {
                cell.setBackground(SEL_BG);
                route.setForeground(PRIMARY_DK);
            } else {
                cell.setBackground(index % 2 == 0 ? Color.WHITE : new Color(248, 250, 252));
                route.setForeground(new Color(15, 23, 42));
            }
            cell.add(route);
            cell.add(sub);
            return cell;
        }
    }

    // ── details panel ────────────────────────────────────────────────────────

    private void showPlaceholder() {
        detailsPanel.removeAll();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        JLabel lbl = new JLabel("✈  Select a flight to see details");
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lbl.setForeground(MUTED);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(Box.createVerticalGlue());
        detailsPanel.add(lbl);
        detailsPanel.add(Box.createVerticalGlue());
        detailsPanel.revalidate();
        detailsPanel.repaint();
    }

    private void showDetails() {
        int idx = flightList.getSelectedIndex();
        if (idx < 0) { showPlaceholder(); return; }
        Flight f = airport.getFlights().get(idx);
        Plane  p = f.getPlane();

        detailsPanel.removeAll();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        // ── route banner (blue) ───────────────────────────────────────────────
        JPanel banner = new JPanel(new BorderLayout(0, 10));
        banner.setBackground(PRIMARY);
        banner.setBorder(BorderFactory.createEmptyBorder(22, 26, 22, 26));
        banner.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        banner.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel routeRow = new JPanel(new BorderLayout());
        routeRow.setOpaque(false);
        JLabel depCode  = new JLabel(f.getDeparture());
        depCode.setFont(new Font("SansSerif", Font.BOLD, 30));
        depCode.setForeground(Color.WHITE);
        JLabel arrowLbl = new JLabel("──── ✈ ────", JLabel.CENTER);
        arrowLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        arrowLbl.setForeground(new Color(147, 197, 253));
        JLabel arrCode  = new JLabel(f.getArrival(), JLabel.RIGHT);
        arrCode.setFont(new Font("SansSerif", Font.BOLD, 30));
        arrCode.setForeground(Color.WHITE);
        routeRow.add(depCode,  BorderLayout.WEST);
        routeRow.add(arrowLbl, BorderLayout.CENTER);
        routeRow.add(arrCode,  BorderLayout.EAST);

        JPanel timeRow = new JPanel(new BorderLayout());
        timeRow.setOpaque(false);
        JLabel depTime = new JLabel(epochToString(f.getTimeDeparture()));
        depTime.setFont(new Font("SansSerif", Font.PLAIN, 12));
        depTime.setForeground(new Color(147, 197, 253));
        JLabel arrTime = new JLabel(epochToString(f.getTimeArrival()), JLabel.RIGHT);
        arrTime.setFont(new Font("SansSerif", Font.PLAIN, 12));
        arrTime.setForeground(new Color(147, 197, 253));
        timeRow.add(depTime, BorderLayout.WEST);
        timeRow.add(arrTime, BorderLayout.EAST);

        banner.add(routeRow, BorderLayout.NORTH);
        banner.add(timeRow,  BorderLayout.SOUTH);

        // ── info card (white) ─────────────────────────────────────────────────
        JPanel infoCard = new JPanel();
        infoCard.setLayout(new BoxLayout(infoCard, BoxLayout.Y_AXIS));
        infoCard.setBackground(Color.WHITE);
        infoCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10),
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COL),
                BorderFactory.createEmptyBorder(14, 20, 14, 20))));
        infoCard.setAlignmentX(Component.LEFT_ALIGNMENT);

        String planeType = (p instanceof CargoPlane) ? "Cargo  📦" : "Airline  🛫";
        addSectionTitle(infoCard, "FLIGHT");
        addInfoRow(infoCard, "Departure",  f.getDeparture());
        addInfoRow(infoCard, "Arrival",    f.getArrival());
        addInfoRow(infoCard, "Dep. time",  epochToString(f.getTimeDeparture()));
        addInfoRow(infoCard, "Arr. time",  epochToString(f.getTimeArrival()));
        addDetailsDivider(infoCard);
        addSectionTitle(infoCard, "AIRCRAFT");
        addInfoRow(infoCard, "Name",         p.getName());
        addInfoRow(infoCard, "Type",         planeType);
        addInfoRow(infoCard, "Pilot",        p.getPilot());
        addInfoRow(infoCard, "Co-pilot",     p.getCoPilot());
        addInfoRow(infoCard, "Total weight", String.format("%.0f kg", p.getTotWeight()));
        if (p instanceof CargoPlane) {
            addInfoRow(infoCard, "Cargo weight",
                    String.format("%.0f kg", (double) ((CargoPlane) p).getCargoWeight()));
        } else if (p instanceof LinePlane) {
            LinePlane lp = (LinePlane) p;
            addInfoRow(infoCard, "Passengers", String.valueOf(lp.getPassengers()));
            addInfoRow(infoCard, "Cabin crew",  String.valueOf(lp.getCabinCrew()));
        }

        detailsPanel.add(banner);
        detailsPanel.add(infoCard);
        detailsPanel.add(Box.createVerticalGlue());
        detailsPanel.revalidate();
        detailsPanel.repaint();
    }

    private void addSectionTitle(JPanel panel, String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 10));
        lbl.setForeground(MUTED);
        lbl.setBorder(BorderFactory.createEmptyBorder(10, 0, 6, 0));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        lbl.setMaximumSize(new Dimension(Integer.MAX_VALUE, lbl.getPreferredSize().height + 16));
        panel.add(lbl);
    }

    private void addInfoRow(JPanel panel, String key, String value) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(Color.WHITE);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        row.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
        JLabel keyLbl = new JLabel(key);
        keyLbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
        keyLbl.setForeground(MUTED);
        keyLbl.setPreferredSize(new Dimension(130, 20));
        JLabel valLbl = new JLabel(value);
        valLbl.setFont(new Font("SansSerif", Font.BOLD, 13));
        valLbl.setForeground(TEXT_DARK);
        row.add(keyLbl, BorderLayout.WEST);
        row.add(valLbl, BorderLayout.CENTER);
        panel.add(row);
    }

    private void addDetailsDivider(JPanel panel) {
        panel.add(Box.createVerticalStrut(8));
        JSeparator sep = new JSeparator();
        sep.setForeground(BORDER_COL);
        sep.setAlignmentX(Component.LEFT_ALIGNMENT);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        panel.add(sep);
    }

    // ── add dialog ───────────────────────────────────────────────────────────

    private void showAddDialog() {
        JDialog dialog = new JDialog(this, "Add Flight", true);
        dialog.setSize(470, 570);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        // dialog header
        JPanel dHead = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        dHead.setBackground(PRIMARY);
        dHead.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));
        JLabel dTitle = new JLabel("✈   New Flight");
        dTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        dTitle.setForeground(Color.WHITE);
        dHead.add(dTitle);
        dialog.add(dHead, BorderLayout.NORTH);

        // form
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createEmptyBorder(18, 22, 18, 22));

        GridBagConstraints lc = new GridBagConstraints();
        lc.gridx = 0; lc.anchor = GridBagConstraints.WEST;
        lc.insets = new Insets(6, 0, 6, 14);

        GridBagConstraints fc = new GridBagConstraints();
        fc.gridx = 1; fc.fill = GridBagConstraints.HORIZONTAL;
        fc.weightx = 1; fc.insets = new Insets(6, 0, 6, 0);

        JTextField depField     = formField();
        JTextField arrField     = formField();
        JTextField nameField    = formField();
        JTextField pilotField   = formField();
        JTextField coPilotField = formField();
        DateTimePicker depPicker = new DateTimePicker(LocalDateTime.now());
        DateTimePicker arrPicker = new DateTimePicker(LocalDateTime.now().plusHours(4));

        String[]     lbls = { "Departure city:", "Arrival city:",
                               "Departure date / time:", "Arrival date / time:",
                               "Plane name:", "Pilot:", "Co-pilot:" };
        JComponent[] flds = { depField, arrField, depPicker, arrPicker,
                               nameField, pilotField, coPilotField };

        for (int i = 0; i < flds.length; i++) {
            lc.gridy = fc.gridy = i;
            form.add(formLabel(lbls[i]), lc);
            form.add(flds[i], fc);
        }

        // plane type
        JRadioButton cargoRb = new JRadioButton("📦  Cargo", true);
        JRadioButton linerRb = new JRadioButton("🛫  Airline");
        styleRadio(cargoRb); styleRadio(linerRb);
        ButtonGroup bg = new ButtonGroup();
        bg.add(cargoRb); bg.add(linerRb);
        JPanel radioRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        radioRow.setBackground(Color.WHITE);
        radioRow.add(cargoRb); radioRow.add(linerRb);
        lc.gridy = fc.gridy = flds.length;
        form.add(formLabel("Plane type:"), lc);
        form.add(radioRow, fc);

        // cargo fields
        JTextField cwField = formField(); cwField.setText("1000");
        JPanel cargoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        cargoPanel.setBackground(Color.WHITE);
        cargoPanel.add(formLabel("Weight (kg):")); cargoPanel.add(cwField);

        // liner fields
        JTextField passField = formField(); passField.setText("150");
        JTextField crewField = formField(); crewField.setText("6");
        passField.setPreferredSize(new Dimension(65, 28));
        crewField.setPreferredSize(new Dimension(50, 28));
        JPanel linerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        linerPanel.setBackground(Color.WHITE);
        linerPanel.add(formLabel("Passengers:")); linerPanel.add(passField);
        linerPanel.add(formLabel("  Crew:"));     linerPanel.add(crewField);

        JPanel typeCard = new JPanel(new CardLayout());
        typeCard.setBackground(Color.WHITE);
        typeCard.add(cargoPanel, "cargo");
        typeCard.add(linerPanel, "liner");
        CardLayout cl = (CardLayout) typeCard.getLayout();
        cargoRb.addActionListener(e -> cl.show(typeCard, "cargo"));
        linerRb.addActionListener(e -> cl.show(typeCard, "liner"));

        GridBagConstraints span = new GridBagConstraints();
        span.gridx = 0; span.gridy = flds.length + 1;
        span.gridwidth = 2; span.fill = GridBagConstraints.HORIZONTAL;
        span.insets = new Insets(4, 0, 4, 0);
        form.add(typeCard, span);

        // action buttons
        JButton okBtn     = styledBtn("✓  Add Flight", PRIMARY);
        JButton cancelBtn = styledBtn("Cancel", new Color(148, 163, 184));
        cancelBtn.addActionListener(e -> dialog.dispose());
        okBtn.addActionListener(e -> {
            try {
                if (depField.getText().isBlank() || arrField.getText().isBlank()
                        || nameField.getText().isBlank() || pilotField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(dialog,
                            "Please fill all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (arrPicker.getEpochMilli() <= depPicker.getEpochMilli()) {
                    JOptionPane.showMessageDialog(dialog,
                            "Arrival date/time must be after departure.", "Invalid dates",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Plane plane;
                if (cargoRb.isSelected()) {
                    plane = new CargoPlane(
                            nameField.getText().trim(), pilotField.getText().trim(),
                            coPilotField.getText().trim(),
                            Float.parseFloat(cwField.getText().trim()));
                } else {
                    plane = new LinePlane(
                            nameField.getText().trim(), pilotField.getText().trim(),
                            coPilotField.getText().trim(),
                            Integer.parseInt(passField.getText().trim()),
                            Integer.parseInt(crewField.getText().trim()));
                }
                airport.addFlight(new Flight(plane,
                        depField.getText().trim(), arrField.getText().trim(),
                        depPicker.getEpochMilli(), arrPicker.getEpochMilli()));
                refreshList();
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog,
                        "Invalid number: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 10));
        btnPanel.setBackground(LIGHT_BG);
        btnPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_COL));
        btnPanel.add(cancelBtn); btnPanel.add(okBtn);

        dialog.add(new JScrollPane(form), BorderLayout.CENTER);
        dialog.add(btnPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private JTextField formField() {
        JTextField f = new JTextField(18);
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COL),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        return f;
    }

    private JLabel formLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.PLAIN, 13));
        l.setForeground(MUTED);
        return l;
    }

    private void styleRadio(JRadioButton r) {
        r.setFont(new Font("SansSerif", Font.PLAIN, 13));
        r.setBackground(Color.WHITE);
    }

    // ── list operations ──────────────────────────────────────────────────────

    private void removeFlight() {
        int idx = flightList.getSelectedIndex();
        if (idx < 0) return;
        airport.removeFlight(idx);
        refreshList();
        showPlaceholder();
    }

    private void refreshList() {
        int sel = flightList.getSelectedIndex();
        listModel.clear();
        for (Flight f : airport.getFlights()) listModel.addElement(f);
        if (!listModel.isEmpty())
            flightList.setSelectedIndex(
                Math.min(sel < 0 ? listModel.size() - 1 : sel, listModel.size() - 1));
    }

    private String epochToString(long epoch) {
        if (epoch == 0) return "N/A";
        return Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).format(DISPLAY_FMT);
    }

    // ── DateTimePicker ───────────────────────────────────────────────────────

    static class DateTimePicker extends JPanel {

        private static final Color CAL_PRI    = new Color(37,  99,  235);
        private static final Color CAL_TODAY  = new Color(254, 215, 170);
        private static final Color CAL_HOVER  = new Color(219, 234, 254);
        private static final Color CAL_BG     = new Color(241, 245, 249);
        private static final Color CAL_BORDER = new Color(203, 213, 225);
        private static final Color CAL_MUTED  = new Color(100, 116, 139);
        private static final DateTimeFormatter BTN_FMT =
                DateTimeFormatter.ofPattern("dd MMM yyyy  HH:mm");

        private LocalDateTime selected;
        private final JButton displayBtn;

        DateTimePicker(LocalDateTime initial) {
            this.selected = initial;
            setLayout(new BorderLayout(4, 0));
            setOpaque(false);

            displayBtn = new JButton(selected.format(BTN_FMT));
            displayBtn.setFont(new Font("SansSerif", Font.PLAIN, 13));
            displayBtn.setHorizontalAlignment(SwingConstants.LEFT);
            displayBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            displayBtn.addActionListener(e -> openCalendar());
            add(displayBtn, BorderLayout.CENTER);

            JLabel icon = new JLabel(" 📅");
            icon.setFont(new Font("SansSerif", Font.PLAIN, 16));
            add(icon, BorderLayout.EAST);
        }

        long getEpochMilli() {
            return selected.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }

        private void openCalendar() {
            JDialog popup = new JDialog(
                    SwingUtilities.getWindowAncestor(this),
                    Dialog.ModalityType.APPLICATION_MODAL);
            popup.setUndecorated(true);
            popup.setLayout(new BorderLayout());
            popup.getRootPane().setBorder(BorderFactory.createLineBorder(CAL_BORDER));

            int[] year   = {selected.getYear()};
            int[] month  = {selected.getMonthValue()};
            int[] selDay = {selected.getDayOfMonth()};

            // calendar header
            JLabel monthLbl = new JLabel("", JLabel.CENTER);
            monthLbl.setForeground(Color.WHITE);
            monthLbl.setFont(new Font("SansSerif", Font.BOLD, 14));
            JButton prevBtn = calNavBtn("◀");
            JButton nextBtn = calNavBtn("▶");

            JPanel calHead = new JPanel(new BorderLayout(4, 0));
            calHead.setBackground(CAL_PRI);
            calHead.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
            calHead.add(prevBtn,  BorderLayout.WEST);
            calHead.add(monthLbl, BorderLayout.CENTER);
            calHead.add(nextBtn,  BorderLayout.EAST);

            // day grid
            JPanel grid = new JPanel(new GridLayout(7, 7, 3, 3));
            grid.setBackground(Color.WHITE);
            grid.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

            // time + confirm
            JSpinner hourSpin = new JSpinner(new SpinnerNumberModel(selected.getHour(),   0, 23, 1));
            JSpinner minSpin  = new JSpinner(new SpinnerNumberModel(selected.getMinute(), 0, 59, 1));
            ((JSpinner.DefaultEditor) hourSpin.getEditor()).getTextField().setColumns(2);
            ((JSpinner.DefaultEditor) minSpin.getEditor()).getTextField().setColumns(2);

            JButton confirmBtn = new JButton("Confirm ✓");
            confirmBtn.setBackground(CAL_PRI);
            confirmBtn.setForeground(Color.WHITE);
            confirmBtn.setFocusPainted(false);
            confirmBtn.setBorderPainted(false);
            confirmBtn.setOpaque(true);
            confirmBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
            confirmBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            confirmBtn.addActionListener(e -> {
                selected = LocalDateTime.of(year[0], month[0], selDay[0],
                        (int) hourSpin.getValue(), (int) minSpin.getValue());
                displayBtn.setText(selected.format(BTN_FMT));
                popup.dispose();
            });

            JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 8));
            south.setBackground(CAL_BG);
            south.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, CAL_BORDER));
            JLabel timeLbl = new JLabel("🕐  Time:");
            timeLbl.setFont(new Font("SansSerif", Font.PLAIN, 13));
            south.add(timeLbl);
            south.add(hourSpin);
            south.add(new JLabel(":"));
            south.add(minSpin);
            south.add(Box.createHorizontalStrut(10));
            south.add(confirmBtn);

            // grid rebuild (stored in array to allow self-reference from lambda)
            Runnable[] rebuild = {null};
            rebuild[0] = () -> {
                grid.removeAll();
                LocalDate first = LocalDate.of(year[0], month[0], 1);
                monthLbl.setText(
                    first.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                    + "   " + year[0]);

                for (String d : new String[]{"Mo","Tu","We","Th","Fr","Sa","Su"}) {
                    JLabel lbl = new JLabel(d, JLabel.CENTER);
                    lbl.setFont(new Font("SansSerif", Font.BOLD, 11));
                    lbl.setForeground(CAL_MUTED);
                    grid.add(lbl);
                }

                int startDow = first.getDayOfWeek().getValue(); // 1=Mon … 7=Sun
                for (int i = 1; i < startDow; i++) grid.add(new JLabel());

                LocalDate today = LocalDate.now();
                for (int d = 1; d <= first.lengthOfMonth(); d++) {
                    final int day = d;
                    JButton btn = new JButton(String.valueOf(d));
                    btn.setFont(new Font("SansSerif", Font.PLAIN, 12));
                    btn.setFocusPainted(false);
                    btn.setBorderPainted(false);
                    btn.setOpaque(true);
                    btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                    boolean isSel   = (d == selDay[0]);
                    boolean isToday = LocalDate.of(year[0], month[0], d).equals(today);

                    if (isSel) {
                        btn.setBackground(CAL_PRI);
                        btn.setForeground(Color.WHITE);
                    } else if (isToday) {
                        btn.setBackground(CAL_TODAY);
                        btn.setForeground(new Color(15, 23, 42));
                    } else {
                        btn.setBackground(Color.WHITE);
                        btn.setForeground(new Color(15, 23, 42));
                        btn.addMouseListener(new MouseAdapter() {
                            public void mouseEntered(MouseEvent e) { btn.setBackground(CAL_HOVER); }
                            public void mouseExited(MouseEvent e)  { btn.setBackground(Color.WHITE); }
                        });
                    }
                    btn.addActionListener(e -> { selDay[0] = day; rebuild[0].run(); });
                    grid.add(btn);
                }
                grid.revalidate();
                grid.repaint();
            };

            prevBtn.addActionListener(e -> {
                if (--month[0] < 1)  { month[0] = 12; year[0]--; }
                selDay[0] = 1; rebuild[0].run();
            });
            nextBtn.addActionListener(e -> {
                if (++month[0] > 12) { month[0] = 1;  year[0]++; }
                selDay[0] = 1; rebuild[0].run();
            });

            rebuild[0].run();

            popup.add(calHead, BorderLayout.NORTH);
            popup.add(grid,    BorderLayout.CENTER);
            popup.add(south,   BorderLayout.SOUTH);
            popup.pack();

            Point p = displayBtn.getLocationOnScreen();
            popup.setLocation(p.x, p.y + displayBtn.getHeight());
            popup.setVisible(true);
        }

        private JButton calNavBtn(String text) {
            JButton b = new JButton(text);
            b.setForeground(Color.WHITE);
            b.setBackground(CAL_PRI);
            b.setFocusPainted(false);
            b.setBorderPainted(false);
            b.setOpaque(true);
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            return b;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AirportGUI::new);
    }
}
