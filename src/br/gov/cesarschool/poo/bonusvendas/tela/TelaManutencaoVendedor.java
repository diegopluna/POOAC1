    package br.gov.cesarschool.poo.bonusvendas.tela;

    import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
    import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Endereco;
    import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Sexo;
    import br.gov.cesarschool.poo.bonusvendas.negocio.ResultadoInclusaoVendedor;
    import br.gov.cesarschool.poo.bonusvendas.negocio.VendedorMediator;

    import javax.swing.*;
    import javax.swing.text.MaskFormatter;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.text.ParseException;
    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;

    public class TelaManutencaoVendedor extends JFrame {
        private VendedorMediator mediator = VendedorMediator.getInstancia();

        private JPanel panelMain;
        private JFormattedTextField txtCPF;
        private JTextField txtNome;
        private JRadioButton radioMasc;
        private JFormattedTextField txtNascimento;
        private JFormattedTextField txtRenda;
        private JTextField txtLogradouro;
        private JFormattedTextField txtNumero;
        private JTextField txtComplemento;
        private JFormattedTextField txtCEP;
        private JTextField txtCidade;
        private JComboBox<String> comboEstado;
        private JRadioButton radioFem;
        private JButton includeBtn;

        public TelaManutencaoVendedor() {
            try {
                MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
                cpfMask.install(txtCPF);

                MaskFormatter nascMask = new MaskFormatter("##/##/####");
                nascMask.install(txtNascimento);

                MaskFormatter cepMask = new MaskFormatter("##.###-###");
                cepMask.install(txtCEP);

                MaskFormatter rendaMask = new MaskFormatter("##########.##");
                rendaMask.setPlaceholderCharacter(' ');
                rendaMask.install(txtRenda);

                MaskFormatter numberMask = new MaskFormatter("#######");
                numberMask.install(txtNumero);

                String[] estados = {
                        "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará", "Distrito Federal",
                        "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul",
                        "Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro",
                        "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina",
                        "São Paulo", "Sergipe", "Tocantins"
                };

                for (String estado : estados) {
                    comboEstado.addItem(estado);
                }

                ButtonGroup sexoGroup = new ButtonGroup();
                sexoGroup.add(radioMasc);
                sexoGroup.add(radioFem);
                includeBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String cpf = txtCPF.getText().replaceAll("[^0-9]", "");
                        String name = txtNome.getText();
                        Sexo sexo;
                        if (radioMasc.isSelected()) {
                            sexo = Sexo.MASCULINO;
                        } else if (radioFem.isSelected()) {
                            sexo = Sexo.FEMININO;
                        } else {
                            sexo = null;
                        }
                        String nascTexto= txtNascimento.getText().replaceAll("[^0-9]", "");
                        LocalDate nasc = LocalDate.parse(nascTexto, DateTimeFormatter.ofPattern("ddMMyyyy"));
                        String rendaTexto = txtRenda.getText().replaceAll("\\d+(\\.\\d+)?|\\.\\d+", "");
                        double renda = Double.parseDouble(rendaTexto)/100.00;
                        String logradouro = txtLogradouro.getText();
                        int numero = Integer.valueOf(txtNumero.getText().replaceAll("[^0-9]", ""));
                        String complemento = txtComplemento.getText();
                        String cep = txtCEP.getText().replaceAll("[^0-9]", "");
                        String cidade = txtCidade.getText();
                        String estado = comboEstado.getSelectedItem().toString();
                        System.out.println(renda);

                        Endereco endereco = new Endereco(logradouro, numero, complemento, cep, cidade, estado, "Brasil");
                        Vendedor vendedor = new Vendedor(cpf, name, sexo, nasc, renda, endereco);

                        ResultadoInclusaoVendedor resultadoInclusao = mediator.incluir(vendedor);
                        if (resultadoInclusao.getMensagemErroValidacao() == null) {
                            JOptionPane.showMessageDialog(null, "Great Success");
                        } else if (resultadoInclusao.getMensagemErroValidacao().equals("Vendedor ja existente")){
                            String[] options = {"Alterar", "Cancelar"};
                            int result = JOptionPane.showOptionDialog(null,
                                    "Deseja realizar alterações no vendedor?",
                                    "Vendedor ja existente",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    options[0]
                                    );
                            if (result == JOptionPane.YES_OPTION) {
                                String resultadoAlteracao = mediator.alterar(vendedor);
                                if (resultadoAlteracao == null) {
                                    JOptionPane.showMessageDialog(null, "Great Success");
                                } else {
                                    JOptionPane.showMessageDialog(null, resultadoAlteracao);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, resultadoInclusao.getMensagemErroValidacao());
                        }
                    }
                });


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
