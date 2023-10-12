package br.gov.cesarschool.poo.bonusvendas.tela;

import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.TipoResgate;
import br.gov.cesarschool.poo.bonusvendas.negocio.AcumuloResgateMediator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAcumuloResgate extends JFrame {
    private JFormattedTextField txtNumero;
    private JButton btnBuscar;
    private JFormattedTextField txtSaldo;
    private JComboBox comboTipo;
    private JFormattedTextField txtValor;
    private JButton btnAcumuloResgate;
    private JButton btnVoltar;
    private JPanel panelMain;
    private JRadioButton radioResgate;
    private JRadioButton radioCredito;
    private AcumuloResgateMediator mediator = AcumuloResgateMediator.getInstancia();

    public TelaAcumuloResgate() {
        ButtonGroup opGroup = new ButtonGroup();
        opGroup.add(radioCredito);
        opGroup.add(radioResgate);

        String[] tiposResgates = {"Produto", "Serviço", "Cash"};
        for (String tipo: tiposResgates) {
            comboTipo.addItem(tipo);
        }
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long numero = Long.parseLong(txtNumero.getText().replaceAll("[^0-9]", ""));
                if (radioCredito.isSelected()) {
                    CaixaDeBonus caixa = mediator.getRepositorioCaixaDeBonus().buscar(numero);
                    if (caixa == null) {
                        JOptionPane.showMessageDialog(null, "Caixa de bônus não existe");
                    } else {
                        txtNumero.setEditable(false);
                        txtNumero.setEnabled(false);
                        radioResgate.setEnabled(false);
                        radioCredito.setEnabled(false);
                        btnBuscar.setEnabled(false);
                        txtValor.setEnabled(true);
                        txtValor.setEditable(true);
                        btnAcumuloResgate.setText("Creditar");
                        btnAcumuloResgate.setEnabled(true);
                        btnVoltar.setEnabled(true);
                        txtSaldo.setText(String.valueOf(caixa.getSaldo()));
                    }

                } else if (radioResgate.isSelected()) {
                    CaixaDeBonus caixa = mediator.getRepositorioCaixaDeBonus().buscar(numero);
                    if (caixa == null) {
                        JOptionPane.showMessageDialog(null, "Caixa de bônus não existe");
                    } else {
                        txtNumero.setEditable(false);
                        txtNumero.setEnabled(false);
                        radioResgate.setEnabled(false);
                        radioCredito.setEnabled(false);
                        btnBuscar.setEnabled(false);
                        comboTipo.setEnabled(true);
                        txtValor.setEnabled(true);
                        txtValor.setEditable(true);
                        btnAcumuloResgate.setText("Resgatar");
                        btnAcumuloResgate.setEnabled(true);
                        btnVoltar.setEnabled(true);
                        txtSaldo.setText(String.valueOf(caixa.getSaldo()));
                    }
                }
            }
        });

        btnAcumuloResgate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long numero = Long.parseLong(txtNumero.getText().replaceAll("[^0-9]", ""));
                if (radioCredito.isSelected()) {
                    try {
                        double valor = Double.parseDouble(txtValor.getText());
                        String response = mediator.acumularBonus(numero, valor);
                        if (response != null) {
                            JOptionPane.showMessageDialog(null, response);
                        } else {
                            JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
                            txtNumero.setText("");
                            txtNumero.setEditable(true);
                            txtNumero.setEnabled(true);
                            radioResgate.setEnabled(true);
                            radioCredito.setEnabled(true);
                            btnBuscar.setEnabled(true);
                            comboTipo.setEnabled(false);
                            txtValor.setText("");
                            txtValor.setEnabled(false);
                            txtValor.setEditable(false);
                            btnAcumuloResgate.setText("Acumular/Resgatar");
                            btnAcumuloResgate.setEnabled(false);
                            btnVoltar.setEnabled(false);
                            txtSaldo.setText("");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Valor inválido");
                    }

                } else if(radioResgate.isSelected()) {
                    TipoResgate tipo = null;
                    if (comboTipo.getSelectedItem().toString().equals("Produto")) {
                        tipo = TipoResgate.PRODUTO;
                    } else if (comboTipo.getSelectedItem().toString().equals("Serviço")) {
                        tipo = TipoResgate.SERVICO;
                    } else if (comboTipo.getSelectedItem().toString().equals("Cash")) {
                        tipo = TipoResgate.CASH;
                    }
                    try {
                        double valor = Double.parseDouble(txtValor.getText());
                        String response = mediator.resgatar(numero, valor, tipo);
                        if (response != null) {
                            JOptionPane.showMessageDialog(null, response);
                        } else {
                            JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
                            txtNumero.setText("");
                            txtNumero.setEditable(true);
                            txtNumero.setEnabled(true);
                            radioResgate.setEnabled(true);
                            radioCredito.setEnabled(true);
                            btnBuscar.setEnabled(true);
                            comboTipo.setEnabled(false);
                            txtValor.setText("");
                            txtValor.setEnabled(false);
                            txtValor.setEditable(false);
                            btnAcumuloResgate.setText("Acumular/Resgatar");
                            btnAcumuloResgate.setEnabled(false);
                            btnVoltar.setEnabled(false);
                            txtSaldo.setText("");

                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Valor inválido");
                    }
                }
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNumero.setText("");
                txtNumero.setEditable(true);
                txtNumero.setEnabled(true);
                radioResgate.setEnabled(true);
                radioCredito.setEnabled(true);
                btnBuscar.setEnabled(true);
                comboTipo.setEnabled(false);
                txtValor.setText("");
                txtValor.setEnabled(false);
                txtValor.setEditable(false);
                btnAcumuloResgate.setText("Acumular/Resgatar");
                btnAcumuloResgate.setEnabled(false);
                btnVoltar.setEnabled(false);
                txtSaldo.setText("");
            }
        });
    }

    public static void main(String[] args) {
        try {
            TelaAcumuloResgate tela = new TelaAcumuloResgate();
            tela.setContentPane(tela.panelMain);
            tela.setTitle("Acúmulo/Resgate");
            tela.setSize(800, 600);
            tela.setVisible(true);
            tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
