import javax.swing.*; // Importa as classes do Swing para criar a interface grafica
import java.awt.*; // Importa classes do AWT para manipulacao de layouts e eventos
import java.awt.event.ActionEvent; // Importa a classe para manipular eventos de acao
import java.awt.event.ActionListener; // Importa a interface para ouvir eventos de acao
import java.util.ArrayList; // Importa a classe ArrayList para armazenar frutas

public class FrutinhaGUI {
    // Declara um ArrayList para armazenar as frutas
    private ArrayList<String> frutas;
    // Declara um modelo de lista para a JList
    private DefaultListModel<String> listModel;
    // Declara a JList para exibir as frutas
    private JList<String> list;

    // Construtor da classe que inicializa a GUI
    public FrutinhaGUI() {
        frutas = new ArrayList<>(); // Inicializa o ArrayList de frutas
        listModel = new DefaultListModel<>(); // Inicializa o modelo da lista

        // Cria a janela principal da aplicaçao
        JFrame frame = new JFrame("Gerenciador de Frutas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar a janela
        frame.setSize(600, 300); // Define o tamanho da janela
        frame.setLayout(new BorderLayout()); // Define o layout como BorderLayout

        // Cria um painel para os componentes de entrada
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout()); // Define o layout do painel como FlowLayout

        // Cria um campo de texto para entrada de frutas
        JTextField fruitField = new JTextField(15);
        panel.add(fruitField); // Adiciona o campo ao painel

        // Cria botões para adicionar, modificar e remover frutas
        JButton addButton = new JButton("Adicionar");
        panel.add(addButton);

        JButton modifyButton = new JButton("Modificar");
        modifyButton.setEnabled(false); // Inicialmente desabilita o botao de modificar
        panel.add(modifyButton);

        JButton removeButton = new JButton("Remover");
        removeButton.setEnabled(false); // Inicialmente desabilita o botao de remover
        panel.add(removeButton);

        // Adiciona o painel a parte superior da janela
        frame.add(panel, BorderLayout.NORTH);

        // Cria a JList para exibir as frutas
        list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list); // Adiciona rolagem a lista
        frame.add(scrollPane, BorderLayout.CENTER); // Adiciona a lista ao centro da janela

        // Cria um botao para listar todas as frutas
        JButton listButton = new JButton("Listar Frutas");
        frame.add(listButton, BorderLayout.SOUTH); // Adiciona o botao ao rodape da janela

        // Adiciona um listener para o botao "Adicionar"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String novaFruta = fruitField.getText(); // Obtem o texto do campo
                if (!novaFruta.isEmpty()) { // Verifica se o campo nao esta vazio
                    frutas.add(novaFruta); // Adiciona a nova fruta ao ArrayList
                    listModel.addElement(novaFruta); // Adiciona a nova fruta ao modelo da lista
                    fruitField.setText(""); // Limpa o campo de texto
                    JOptionPane.showMessageDialog(frame, novaFruta + " foi adicionada."); // Exibe mensagem de confirmaçao
                }
            }
        });

        // Adiciona um listener para o botao "Modificar"
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex(); // Obtem o indice da fruta selecionada
                if (selectedIndex != -1) { // Verifica se alguma fruta esta selecionada
                    String frutaSelecionada = listModel.getElementAt(selectedIndex); // Obtem a fruta selecionada
                    String novaFruta = JOptionPane.showInputDialog(frame, "Modificar " + frutaSelecionada + " para:", frutaSelecionada); // Pergunta ao usuario a nova fruta
                    if (novaFruta != null && !novaFruta.isEmpty()) { // Verifica se a nova fruta nao e nula ou vazia
                        frutas.set(selectedIndex, novaFruta); // Atualiza a fruta no ArrayList
                        listModel.set(selectedIndex, novaFruta); // Atualiza a fruta no modelo da lista
                        JOptionPane.showMessageDialog(frame, "Fruta " + frutaSelecionada + " foi modificada para " + novaFruta); // Exibe mensagem de confirmaçao
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione uma fruta para modificar."); // Mensagem se nenhuma fruta foi selecionada
                }
            }
        });

        // Adiciona um listener para o botao "Remover"
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex(); // Obtem o indice da fruta selecionada
                if (selectedIndex != -1) { // Verifica se alguma fruta esta selecionada
                    String frutaRemovida = frutas.remove(selectedIndex); // Remove a fruta do ArrayList
                    listModel.remove(selectedIndex); // Remove a fruta do modelo da lista
                    JOptionPane.showMessageDialog(frame, frutaRemovida + " foi removida."); // Exibe mensagem de confirmaçao
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione uma fruta para remover."); // Mensagem se nenhuma fruta foi selecionada
                }
            }
        });

        // Adiciona um listener para o botao "Listar Frutas"
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frutas.isEmpty()) { // Verifica se a lista de frutas esta vazia
                    JOptionPane.showMessageDialog(frame, "Nenhuma fruta na lista."); // Mensagem se nao houver frutas
                } else {
                    JOptionPane.showMessageDialog(frame, "Frutas: " + frutas); // Exibe a lista de frutas
                }
            }
        });

        // Listener para a seleçao da lista
        list.addListSelectionListener(e -> {
            boolean selectionExists = !list.isSelectionEmpty(); // Verifica se ha uma seleçao
            removeButton.setEnabled(selectionExists); // Habilita ou desabilita o botao "Remover"
            modifyButton.setEnabled(selectionExists); // Habilita ou desabilita o botao "Modificar"
        });

        // Torna a janela visivel
        frame.setVisible(true);
    }

    // Metodo principal que inicia a aplicaçao
    public static void main(String[] args) {
        // Executa a criaçao da GUI na thread de eventos do Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrutinhaGUI(); // Cria uma nova instancia do gerenciador de frutas
            }
        });
    }
}
