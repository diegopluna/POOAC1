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
    import java.awt.event.KeyAdapter;
    import java.awt.event.KeyEvent;
    import java.text.ParseException;
    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;
    import java.time.format.DateTimeParseException;

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

        public TelaManutencaoVendedor() throws ParseException {

            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.install(txtCPF);

            MaskFormatter nascMask = new MaskFormatter("##/##/####");
            nascMask.install(txtNascimento);

            MaskFormatter cepMask = new MaskFormatter("##.###-###");
            cepMask.install(txtCEP);

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

            txtRenda.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9') ||
                            (c == KeyEvent.VK_BACK_SPACE) ||
                            (c == KeyEvent.VK_DELETE) ||
                            (c == '.' && !txtRenda.getText().contains(".")))) {
                        e.consume();
                    }
                }
            });

            txtNumero.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if(!((c >= '0') && (c <= '9') ||
                            (c == KeyEvent.VK_BACK_SPACE) ||
                            (c == KeyEvent.VK_DELETE)) ||
                            (txtNumero.getText().length() >= 7)){
                        e.consume();
                    }
                }
            });

            includeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cpf = txtCPF.getText().replaceAll("[^0-9]", "");
                    if (cpf.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo CPF está vazio!");
                        return;
                    }
                    if (txtNome.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo nome está vazio!");
                        return;
                    }
                    String name = txtNome.getText();
                    Sexo sexo;
                    if (radioMasc.isSelected()) {
                        sexo = Sexo.MASCULINO;
                    } else if (radioFem.isSelected()) {
                        sexo = Sexo.FEMININO;
                    } else {
                        JOptionPane.showMessageDialog(null, "Sexo não selecionado!");
                        return;
                    }
                    String nascTexto= txtNascimento.getText().replaceAll("[^0-9]", "");
                    LocalDate nasc = null;
                    try {
                        nasc = LocalDate.parse(nascTexto, DateTimeFormatter.ofPattern("ddMMyyyy"));
                        if (LocalDate.now().isBefore(nasc)) {
                            throw new Exception("Data de nascimento inválida!");

                        }
                    } catch (DateTimeParseException dateTimeParseException) {
                        JOptionPane.showMessageDialog(null, "Data de nascimento inválida!");
                        return;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                        return;
                    }
                    if (txtRenda.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo renda está vazio!");
                        return;
                    }
                    double renda = Double.parseDouble(txtRenda.getText());
                    if (txtLogradouro.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo logradouro está vazio!");
                        return;
                    }
                    String logradouro = txtLogradouro.getText();
                    if (txtNumero.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo número está vazio!");
                        return;
                    }
                    int numero = Integer.parseInt(txtNumero.getText());
                    if (txtComplemento.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo complemento está vazio!");
                        return;
                    }
                    String complemento = txtComplemento.getText();
                    String cep = txtCEP.getText().replaceAll("[^0-9]", "");
                    if (cep.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo cep está vazio!");
                        return;
                    }
                    String cidade = txtCidade.getText();
                    if (cidade.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo cidade está vazio!");
                        return;
                    }
                    String estado = comboEstado.getSelectedItem().toString();
                    if (estado.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Estado não foi selecionado!");
                    }
                        Endereco endereco = new Endereco(logradouro, numero, complemento, cep, cidade, estado, "Brasil");
                        Vendedor vendedor = new Vendedor(cpf, name, sexo, nasc, renda, endereco);

                        ResultadoInclusaoVendedor resultadoInclusao = mediator.incluir(vendedor);
                        if (resultadoInclusao.getMensagemErroValidacao() == null) {
                            JOptionPane.showMessageDialog(null, "Vendedor incluído com sucesso!");
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
                                    JOptionPane.showMessageDialog(null, "Vendedor alterado com sucesso!");
                                } else {
                                    JOptionPane.showMessageDialog(null, resultadoAlteracao);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, resultadoInclusao.getMensagemErroValidacao());
                        }
                }
            });





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
