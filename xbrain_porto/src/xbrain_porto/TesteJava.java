package xbrain_porto;
/**
 *
 * @author Adelcio C.Porto
 */

import enums.Direction;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TesteJava extends JFrame
{
    private JPanel contentPane;
    private JLabel positionLabel, directionLabel, spaceLabel;
    private JLabel displacementLabel, lengthGridLabel;
    private JTextField displacementText, lengthGridText;
    private JButton borderButton, recreateButton;
    private JButton rightButton, leftButton, upButton, downButton;
    private static int gridSize = 10;
    private String lastPosition = "0,0";
    private boolean showBorder = true;
    private Icon icon, iconNone;
    private JPanel upPanel, downPanel, middlePanel, buttonPanel, recreatePanel, spacePanel1, spacePanel2, spacePanel3, spacePanel4, borderPanel;
    private int widthButton = 100;
    private int heightButton = 30;
    private int sizeFontButton = 12;

    public TesteJava()
    {
        super("Teste Java - Adelcio Porto");
    }

    private void createAndDisplayGUI()
    {       
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        contentPane             = new JPanel();
        contentPane             .setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        contentPane             .setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        
        icon                    = new ImageIcon(getClass().getResource("robot.gif"));
        iconNone                = new ImageIcon(getClass().getResource(""));
        javax.swing             .border.Border emptyBorder = BorderFactory.createEmptyBorder();

        JPanel leftPanel        = new JPanel();
        leftPanel               .setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        leftPanel               .setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));    

        JPanel labelPanel       = new JPanel();
        positionLabel           = new JLabel("", JLabel.LEFT);
        JPanel directionPanel   = new JPanel();
        directionLabel          = new JLabel("", JLabel.LEFT);
        labelPanel              .add(positionLabel);
        directionPanel          .add(directionLabel);

        JPanel displacementPanel = new JPanel();
        displacementLabel       = new JLabel("Deslocamento...:", JLabel.LEFT);
        displacementText        = new JTextField();
        displacementText        .setPreferredSize(new Dimension(40, 20));
        displacementText        .setFont(new Font("Arial",Font.BOLD,12));
        displacementText        .setHorizontalAlignment(JTextField.RIGHT);
        displacementText        .setForeground(Color.blue);
        displacementText        .setText("1");

        JPanel lengthGridPanel  = new JPanel();
        lengthGridLabel         = new JLabel("Tamanho do Grid.:", JLabel.LEFT);
        lengthGridText           = new JTextField();
        lengthGridText          .setPreferredSize(new Dimension(40, 20));
        lengthGridText          .setFont(new Font("Arial",Font.BOLD,12));
        lengthGridText          .setHorizontalAlignment(JTextField.RIGHT);
        lengthGridText          .setForeground(Color.blue);
        lengthGridText          .setText("10");
        
        buttonPanel             = new JPanel();
        buttonPanel             .setLayout(new GridLayout(gridSize, gridSize, 0, 0));
        JPanel buttonLeftPanel  = new JPanel();
        buttonLeftPanel         .setLayout(new BorderLayout()); 
        recreatePanel           = new JPanel();
        recreatePanel           .setLayout(new FlowLayout());
        recreatePanel           .setSize(widthButton * 2, 30); 
        recreateButton          = new JButton("Recriar Grid");
        recreateButton          .setPreferredSize(new Dimension(recreatePanel.getWidth(), recreatePanel.getHeight()));
        recreateButton          .setFont(new Font("Arial",Font.BOLD,12));
        recreateButton          .addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                try {
                    gridSize            = Integer.parseInt(lengthGridText.getText());
                    contentPane         .remove(buttonPanel);
                    Component[] comps   = buttonPanel.getComponents();
                    for (Component comp : comps) {
                        buttonPanel     .remove(comp);
                    }
                    buttonPanel         .setLayout(new GridLayout(gridSize, gridSize, 0, 0));
                    createGrid();
                    setContentPane(contentPane);
                    SwingUtilities      .updateComponentTreeUI(contentPane);
                    pack();
                    setLocationRelativeTo(null);
                    setVisible(true);
                } catch (NumberFormatException e) {
                    JOptionPane         .showMessageDialog(null, "Tamanho do Grid precisa ser inteiro!");
                }
             }
       });

        spacePanel1              = new JPanel();
        spacePanel1              .setLayout(new FlowLayout());
        spacePanel1              .setPreferredSize(new Dimension(widthButton * 2, 30)); 

        spacePanel2              = new JPanel();
        spacePanel2              .setLayout(new FlowLayout());
        spacePanel2              .setPreferredSize(new Dimension(widthButton * 2, 30)); 

        spacePanel3              = new JPanel();
        spacePanel3              .setLayout(new FlowLayout());
        spacePanel3              .setPreferredSize(new Dimension(widthButton * 2, 30)); 

        spacePanel4              = new JPanel();
        spacePanel4              .setLayout(new FlowLayout());
        spacePanel4              .setPreferredSize(new Dimension(widthButton * 2, 30)); 

        borderPanel             = new JPanel();
        borderPanel             .setLayout(new FlowLayout());
        borderPanel             .setSize(widthButton * 2, 30); 
        borderButton            = new JButton("Oculta o Grid");
        borderButton            .setPreferredSize(new Dimension(recreatePanel.getWidth(), recreatePanel.getHeight()));
        borderButton            .setFont(new Font("Arial",Font.BOLD,12));
        borderButton            .addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                Component[] comps   = buttonPanel.getComponents();
                JButton jbt = new JButton();
                for (Component comp : comps) {
                    jbt = (JButton) comp;
                    if (showBorder) {
                        jbt.setBorder(emptyBorder);
                        borderButton.setText("Exibe o Grid");
                    } else {
                        jbt.setBorder(new LineBorder(Color.GRAY));
                        borderButton.setText("Oculta o Grid");
                    }
                }
                showBorder = !showBorder;
             }
       });


        upPanel             = new JPanel();
        upPanel             .setLayout(new FlowLayout());
        upPanel             .setPreferredSize(new Dimension(widthButton * 2, heightButton+5)); 

        upButton            = new JButton(Direction.UP.getDirection());
        upButton            .setPreferredSize(new Dimension(widthButton, heightButton));
        upButton            .setFont(new Font("Arial",Font.BOLD,sizeFontButton));
        upButton            .addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                moveRobot(Direction.UP.name());
                directionLabel      .setText("Direção: " + Direction.UP.getDirection());
             }
       });
        upPanel             .add(upButton);
        
        downPanel           = new JPanel();
        downPanel           .setLayout(new FlowLayout());
        downPanel           .setPreferredSize(new Dimension(widthButton * 2, heightButton+5)); 

        downButton          = new JButton(Direction.DOWN.getDirection());
        downButton          .setPreferredSize(new Dimension(widthButton, heightButton));
        downButton          .setFont(new Font("Arial",Font.BOLD,sizeFontButton));
        downButton          .addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                moveRobot(Direction.DOWN.name());
                directionLabel      .setText("Direção: " + Direction.DOWN.getDirection());
             }
       });
        downPanel           .add(downButton);
        
        middlePanel         = new JPanel();
        middlePanel         .setLayout(new FlowLayout());
        middlePanel         .setPreferredSize(new Dimension(widthButton * 2, heightButton+5)); 

        leftButton          = new JButton(Direction.LEFT.getDirection());
        leftButton          .setPreferredSize(new Dimension(widthButton, heightButton));
        leftButton          .setFont(new Font("Arial",Font.BOLD,sizeFontButton));
        leftButton          .addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                moveRobot(Direction.LEFT.name());
                directionLabel      .setText("Direção: " + Direction.LEFT.getDirection());
             }
       });
        middlePanel         .add(leftButton);
        
        rightButton         = new JButton(Direction.RIGHT.getDirection());
        rightButton         .setPreferredSize(new Dimension(widthButton, heightButton));
        rightButton         .setFont(new Font("Arial",Font.BOLD,sizeFontButton));
        rightButton         .addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                moveRobot(Direction.RIGHT.name());
                directionLabel      .setText("Direção: " + Direction.RIGHT.getDirection());
             }
       });
        middlePanel         .add(rightButton);
        
        leftPanel           .add(labelPanel);
        leftPanel           .add(directionPanel);
        leftPanel           .add(spacePanel1);
        leftPanel           .add(upPanel);
        leftPanel           .add(middlePanel);
        leftPanel           .add(downPanel);
        displacementPanel   .add(displacementLabel);
        displacementPanel   .add(displacementText);
        leftPanel           .add(spacePanel2);
        leftPanel           .add(displacementPanel);
        lengthGridPanel     .add(lengthGridLabel);
        lengthGridPanel     .add(lengthGridText);
        leftPanel           .add(lengthGridPanel);
        recreatePanel       .add(recreateButton);
        leftPanel           .add(recreatePanel);
        leftPanel           .add(spacePanel3);
        borderPanel         .add(borderButton);
        leftPanel           .add(borderPanel);
        leftPanel           .add(spacePanel4);
        contentPane         .add(leftPanel);
        
        createGrid();

        setContentPane(contentPane);
        pack();
        setLocationByPlatform(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void createGrid() {
        for (int i = 0; i < gridSize; i++)
        {
            for (int j = 0; j < gridSize; j++)
            {
                JButton button  = new JButton("");  
                button          .setActionCommand("" + i + ","+ j); 
                //button.setBorder(emptyBorder);
                button          .setPreferredSize(new Dimension(50, 50));
                button          .setOpaque(false);
                button          .setContentAreaFilled(false);
                button          .setBorder(new LineBorder(Color.GRAY));
                button          .addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                    }
                });
                buttonPanel     .add(button);
            }
        }
        contentPane             .add(buttonPanel);
        //set robot on initial position = center
        Component[] comps = buttonPanel.getComponents();
        JButton jbt = new JButton();
        for (Component comp : comps) {
            jbt = (JButton) comp;
            if (jbt.getActionCommand().indexOf("" + (int)(gridSize/2) + "," + (int)(gridSize/2)) != -1) {
                jbt             .requestFocus(); 
                jbt             .setIcon(icon);
                lastPosition    = jbt.getActionCommand();
                positionLabel   .setText("Posição: " + lastPosition);
            }
        }
        directionLabel.setText("Direção:         ");
    }

    private void moveRobot(String direction) {
        int displacement        = 1;
        boolean success = true;
        try {
            displacement        = Integer.parseInt(displacementText.getText());
            if (displacement <= 0) {
                JOptionPane     .showMessageDialog(null, "Valor do deslocamento precisa ser maior que 0 !");
                success         = false;
            }
        } catch (NumberFormatException e) {
            JOptionPane         .showMessageDialog(null, "Valor do deslocamento precisa ser inteiro!");
            success             = false;
        }
        if (success) {
            Component[] comps   = buttonPanel.getComponents();
            JButton jbt         = new JButton();
            for (Component comp : comps) {
                jbt             = (JButton) comp;
                String[] position = lastPosition.split(",");
                int rowNow      = Integer.parseInt(position[0]);
                int colNow      = Integer.parseInt(position[1]);
                if (jbt.getActionCommand().equals(lastPosition)) {
                    jbt         .setIcon(iconNone);
                    int nextRow = rowNow;
                    int nextCol = colNow;
                    if (direction.equals(Direction.UP.name())) {
                        nextRow = nextRow - displacement;
                        if (nextRow < 0) {
                            nextRow = gridSize + nextRow;
                        }
                    } else if (direction.equals(Direction.DOWN.name())) {
                        nextRow = nextRow + displacement;
                        if (nextRow >= gridSize) {
                            nextRow = nextRow - gridSize;
                        }
                    } else if (direction.equals(Direction.LEFT.name())) {
                        nextCol = nextCol - displacement;
                        if (nextCol < 0) {
                            nextCol = gridSize + nextCol;
                        }
                    } else if (direction.equals(Direction.RIGHT.name())) {
                        nextCol = nextCol + displacement;
                        if (nextCol >= gridSize) {
                            nextCol = nextCol - gridSize;
                        }
                    } 
                    lastPosition    = "" + nextRow + "," + nextCol;
                    for (Component comp1 : comps) {
                        jbt = (JButton) comp1;
                        if (jbt.getActionCommand().equals(lastPosition)) {
                            jbt     .setIcon(icon);
                            jbt     .requestFocus();
                            break;
                        }
                    }
                    positionLabel   .setText("Posição: " + lastPosition);
                    break;
                }
            }
        }
    }
    public static void main(String[] args)
    {
        if (args.length > 0)
        {
            gridSize = Integer.parseInt(args[0]);
        }
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new TesteJava().createAndDisplayGUI();
            }
        });
    }
}