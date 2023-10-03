    package br.gov.cesarschool.poo.bonusvendas.tela;

    import br.gov.cesarschool.poo.bonusvendas.negocio.VendedorMediator;

    import javax.swing.*;
    import javax.swing.text.MaskFormatter;
    import javax.swing.text.NumberFormatter;
    import java.text.DecimalFormat;
    import java.text.NumberFormat;
    import java.text.ParseException;
    import java.util.Locale;

    public class TelaManutencaoVendedor extends JFrame {
        private VendedorMediator mediator = VendedorMediator.getInstancia();

        private JPanel panelMain;
        private JFormattedTextField txtCPF;
        private JTextField txtNome;
        private JRadioButton radioMasc;
        private JFormattedTextField txtNascimento;
        private JFormattedTextField txtRenda;
        private JTextField txtLogradouro;
        private JTextField txtNumero;
        private JTextField txtComplemento;
        private JFormattedTextField txtCEP;
        private JTextField txtCidade;
        private JComboBox comboEstado;
        private JRadioButton radioFem;
        private JButton button1;

        public TelaManutencaoVendedor() {
            try {
                MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
                cpfMask.install(txtCPF);

                MaskFormatter nascMask = new MaskFormatter("##/##/####");
                nascMask.install(txtNascimento);

                MaskFormatter cepMask = new MaskFormatter("##.###-###");
                cepMask.install(txtCEP);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            try {
                TelaManutencaoVendedor tela = new TelaManutencaoVendedor();
                tela.setContentPane(tela.panelMain);
                tela.setTitle("Manutenção Vendedor");
                tela.setSize(800, 600);
                tela.setVisible(true);
                tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
