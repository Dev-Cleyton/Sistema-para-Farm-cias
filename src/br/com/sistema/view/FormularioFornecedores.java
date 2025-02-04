/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.sistema.view;

import br.com.sistema.dao.ClientesDAO;
import br.com.sistema.dao.FornecedoresDAO;
import br.com.sistema.model.Fornecedores;
import br.com.sistema.ultilitarios.Ultilitarios;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cleyton
 */
public class FormularioFornecedores extends javax.swing.JDialog {

    /**
     * Salva os dados de um Fornecedores no banco de dados com base nas
     * informações fornecidas pela interface gráfica.
     *
     * Este método permite salvar um novo Fornecedores, capturando as
     * informações dos campos da interface gráfica, verificando se os dados
     * inseridos são válidos, e armazenando-os no banco de dados. O método
     * também inclui uma validação para o campo de número, assegurando que seja
     * inserido um valor numérico válido. Após a operação de salvar, o painel de
     * dados pessoais é limpo para permitir novas inserções.
     *
     * O método segue os seguintes passos:
     *
     * 1. Cria um novo objeto {@link Fornecedores} e define seus atributos com
     * os valores capturados dos campos da interface gráfica. 2. Tenta converter
     * o valor do campo `txtNumero` para um número inteiro. Se houver uma
     * exceção de formato inválido (número mal formatado), exibe uma mensagem de
     * erro e interrompe a execução. 3. Chama o método `salvarDao` da classe
     * {@link FornecedoresDAO} para persistir os dados do cliente no banco de
     * dados. 4. Após o salvamento, utiliza a classe {@link Ultilitarios} para
     * limpar os campos da interface gráfica.
     *
     * Exemplo de uso:
     *
     * ``` Salvar(); ```
     *
     * @see FornecedoresDAO#salvarDao(Fornecedores)
     * @see Ultilitarios#LimparTela(javax.swing.JPanel)
     */
    public void Salvar() {
        // 1º Criar um novo objeto Fornecedores e definir seus atributos a partir dos campos da interface gráfica
        Fornecedores obj = new Fornecedores();
        obj.setNome(txtNome.getText());
        obj.setCnpj(txtCnpj.getText());
        obj.setEmail(txtEmail.getText());
        obj.setTelefone(txtTelefone.getText());
        obj.setCelular(txtCelular.getText());
        obj.setCep(txtCep.getText());
        obj.setEndereco(txtEndereco.getText());

        // Tenta converter o campo 'Número' para inteiro, validando se é um valor numérico
        try {
            obj.setNumero(Integer.valueOf(txtNumero.getText()));
        } catch (NumberFormatException e) {
            // Exibe uma mensagem de erro se o campo 'Número' não for um número válido e interrompe a execução
            JOptionPane.showMessageDialog(this, "Número inválido! Por favor, insira um número válido.");
            return;
        }

        // Define os demais campos do objeto cliente
        obj.setComplemento(txtComplento.getText());
        obj.setBairro(txtBairro.getText());
        obj.setCidade(txtCidade.getText());

        // Obtém o estado selecionado no combobox
        String UF = cdEstados.getSelectedItem().toString();
        obj.setEstado(UF);

        // 2º Salvar no banco de dados
        FornecedoresDAO dao = new FornecedoresDAO();
        dao.SalvarFornecedoresDao(obj);

        // 3º Limpar o painel de dados pessoais após salvar o Fornecedores
        Ultilitarios util = new Ultilitarios();
        util.LimparTela(painel_dados_pessoais);
    }

    /**
     * Edita os dados de um Fornecedores existente no banco de dados.
     *
     * Este método coleta os dados preenchidos nos campos da interface gráfica e
     * os utiliza para atualizar as informações de um cliente no banco de dados.
     * Ele valida o campo "Número" para garantir que seja um valor numérico
     * antes de prosseguir com a edição.
     *
     * Passos do método:
     *
     * 1. Coleta os dados da interface gráfica (campos de texto) e atribui ao
     * objeto {@link Fornecedores}. 2. Valida o campo "Número", exibindo uma
     * mensagem de erro caso o valor inserido seja inválido. 3. Atualiza os
     * dados do Fornecedores no banco de dados utilizando o método
     * {@link FornecedoresDAO#EditarDao(Fornecedores)}. 4. Após a edição, o
     * formulário é limpo para evitar duplicação de dados na interface.
     *
     * Exemplo de uso:
     *
     * ``` // O usuário preenche os campos da interface gráfica e pressiona o
     * botão de editar editar(); ```
     *
     * @see Fornecedores
     * @see FornecedoresDAO
     */
    public void Editar() {
        // 1º Criar um novo objeto Fornecedores e definir seus atributos a partir dos campos da interface gráfica
        Fornecedores obj = new Fornecedores();
        obj.setNome(txtNome.getText());
        obj.setCnpj(txtCnpj.getText());
        obj.setEmail(txtEmail.getText());
        obj.setTelefone(txtTelefone.getText());
        obj.setCelular(txtCelular.getText());
        obj.setCep(txtCep.getText());
        obj.setEndereco(txtEndereco.getText());

        // Tenta converter o campo 'Número' para inteiro, validando se é um valor numérico
        try {
            obj.setNumero(Integer.valueOf(txtNumero.getText()));
        } catch (NumberFormatException e) {
            // Exibe uma mensagem de erro se o campo 'Número' não for um número válido e interrompe a execução
            JOptionPane.showMessageDialog(this, "Número inválido! Por favor, insira um número válido.");
            return;
        }

        // Define os demais campos do objeto cliente
        obj.setComplemento(txtComplento.getText());
        obj.setBairro(txtBairro.getText());
        obj.setCidade(txtCidade.getText());

        // Obtém o estado selecionado no combobox
        String UF = cdEstados.getSelectedItem().toString();
        obj.setEstado(UF);
        obj.setId(Integer.parseInt(txtCodigo.getText())); // ID do cliente a ser editado

        // 2º Atualizar os dados do Fornecedores no banco de dados
        FornecedoresDAO dao = new FornecedoresDAO();
        dao.EditarFornecedoreDao(obj);

        // 3º Limpar o painel de dados pessoais após salvar o cliente
        Ultilitarios util = new Ultilitarios();
        util.LimparTela(painel_dados_pessoais);
    }

    /**
     * Exclui um Fornecedores da interface gráfica e do banco de dados.
     *
     * Este método exclui um Fornecedores com base no ID fornecido pelo campo
     * `txtCodigo` da interface gráfica. Após a exclusão, o painel de dados
     * pessoais é limpo.
     *
     * Passos do método:
     *
     * 1. Obtém o ID do cliente a ser excluído a partir do campo `txtCodigo`. 2.
     * Cria um objeto {@link Fornecedores} e define o ID. 3. Chama o método
     * `ExcluirDao` da classe {@link FornecedoresDAO} para realizar a exclusão
     * no banco de dados. 4. Após a exclusão, limpa todos os campos da interface
     * gráfica usando a classe {@link Ultilitarios}.
     *
     * Exemplo de uso:
     *
     * ``` // Pressionando um botão de excluir na interface gráfica Excluir();
     * ```
     *
     * @see Fornecedores
     * @see FornecedoresDAO
     * @see Ultilitarios
     */
    public void Excluir() {
        // 1º Obter o ID do Fornecedores a partir do campo 'txtCodigo'
        Fornecedores obj = new Fornecedores();
        obj.setId(Integer.valueOf(txtCodigo.getText()));

        // 2º Criar uma instância de FornecedoresDAO e chamar o método ExcluirDao para remover o Fornecedores
        FornecedoresDAO dao = new FornecedoresDAO();
        dao.ExcluirFornecedoresDao(obj);

        // 3º Limpar todos os campos do painel de dados pessoais após a exclusão
        Ultilitarios util = new Ultilitarios();
        util.LimparTela(painel_dados_pessoais);
    }

    /**
     * Realiza a pesquisa de um Fornecedores com base no nome fornecido e exibe
     * seus dados na interface gráfica.
     *
     * Este método permite pesquisar um Fornecedores pelo nome, preenchendo os
     * campos da interface gráfica com os dados do Fornecedores encontrado. A
     * busca é realizada chamando o método
     * {@link FornecedoresDAO#BuscarFornecedoresDao(String)}, que acessa o banco
     * de dados e retorna os dados do Fornecedores correspondente.
     *
     * O método segue os seguintes passos: 1. Obtém o nome do Fornecedores a ser
     * pesquisado a partir do campo de texto `txtNome`. 2. Cria uma instância de
     * {@link FornecedoresDAO} para acessar os dados do Fornecedore. 3. Chama o
     * método `BuscarFornecedoreDao` com o nome fornecido para obter o cliente
     * do banco de dados. 4. Se um cliente for encontrado (verificado se o nome
     * não é `null`), os campos da interface gráfica são preenchidos com os
     * dados do cliente, como código, nome, RG, CPF, email, telefone, celular,
     * endereço, número, complemento, bairro, cidade e estado. 5. Caso
     * contrário, uma mensagem de alerta é exibida, indicando que o cliente não
     * foi encontrado.
     *
     * @see FornecedoresDAO#BuscarFornecedoreDao(String)
     */
    public void Pesquisar() {
        // Obtém o nome do Fornecedore a ser pesquisado a partir do campo de texto
        String nome = txtNome.getText();

        // Cria uma instância de Clientes e ClientesDAO para acessar os dados do cliente
        Fornecedores obj = new Fornecedores();
        FornecedoresDAO dao = new FornecedoresDAO();

        // Realiza a busca do cliente com base no nome fornecido
        obj = dao.BuscarFornecedoresDao(nome);

        // Verifica se o cliente foi encontrado
        if (obj.getNome() != null) {
            // Preenche os campos da interface gráfica com os dados do Fornecedore
            txtCodigo.setText(String.valueOf(obj.getId()));
            txtNome.setText(obj.getNome());  // Corrigido para definir o nome corretamente
            txtCnpj.setText(obj.getCnpj());    // Corrigido para setar CPF em vez de CEP
            txtEmail.setText(obj.getEmail());
            txtTelefone.setText(obj.getTelefone());
            txtCelular.setText(obj.getCelular());
            txtCep.setText(obj.getCep());
            txtEndereco.setText(obj.getEndereco());
            txtNumero.setText(String.valueOf(obj.getNumero()));
            txtComplento.setText(obj.getComplemento());
            txtBairro.setText(obj.getBairro());
            txtCidade.setText(obj.getCidade());
            cdEstados.setSelectedItem(obj.getEstado());
        } else {
            // Exibe uma mensagem se o cliente não for encontrado
            JOptionPane.showMessageDialog(null, "Fornecedores não encontrado!");
        }
    }

    /**
     * Creates new form FormularioFornecedores
     */
    /**
     * Lista todos os Fornecedores na tabela exibida na interface gráfica.
     *
     * Este método recupera a lista de Fornecedores do banco de dados usando a
     * classe {@link FornecedoresDAO} e atualiza a tabela na interface gráfica com
     * os dados dos Fornecedores.
     *
     * O método segue os seguintes passos: 1. Cria uma instância de
     * {@link FornecedoresDAO} para acessar os dados dos clientes. 2. Obtém a lista
     * de clientes chamando o método {@link FornecedoresDAO#listar()}. 3. Obtém o
     * modelo da tabela associada ao componente {@link JTable} e limpa as linhas
     * existentes. 4. Adiciona uma nova linha para cada Fornecedore na lista,
     * preenchendo a tabela com os dados do cliente.
     *
     * @see ClientesDAO#listar()
     */
    public void listar() {
        // Cria uma instância de FornecedoresDAO para acessar os dados dos clientes
       FornecedoresDAO dao = new FornecedoresDAO();

        // Obtém a lista de clientes do banco de dados
        List<Fornecedores> lista = dao.ListarFornecedoreDao();

        // Obtém o modelo da tabela e limpa todas as linhas existentes
        DefaultTableModel dados = (DefaultTableModel) tabela.getModel();
        dados.setNumRows(0);

        // Adiciona uma nova linha na tabela para cada cliente na lista
        for (Fornecedores c : lista) {
            dados.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getCnpj(),
                c.getEmail(),
                c.getTelefone(),
                c.getCelular(),
                c.getCep(),
                c.getEndereco(),
                c.getNumero(),
                c.getComplemento(),
                c.getBairro(),
                c.getCidade(),
                c.getEstado()
            });            
        }
    }

    /**
     * Filtra e exibe Fornecedores com base no nome fornecido na interface gráfica.
     *
     * Este método realiza uma busca de Fornecedores na tabela `tb_fornecedores` com
     * base no nome fornecido pelo usuário. O nome é obtido a partir do campo de
     * texto `txtPesquisaNome`, e a busca é realizada utilizando caracteres
     * coringa (`%`) para permitir buscas parciais. Após obter os resultados, o
     * método atualiza a tabela na interface gráfica com os dados dos clientes
     * filtrados.
     *
     * O método segue os seguintes passos: 1. Obtém o texto de pesquisa do campo
     * de texto `txtPesquisaNome` e formata o padrão de nome com caracteres
     * coringa (`%`) para realizar a busca parcial. 2. Cria uma instância da
     * classe {@link FornecedoresDAO} para acessar os dados dos Fornecedores. 3. Obtém a
     * lista de Fornecedores filtrados chamando o método
     * {@link FornecedoresDAO#filtar(String)} com o padrão de nome formatado. 4.
     * Obtém o modelo da tabela associada ao componente {@link JTable} e limpa
     * todas as linhas existentes. 5. Adiciona uma nova linha na tabela para
     * cada cliente na lista, preenchendo a tabela com os dados do Fornecedores.
     *
     * @see FornecedoresDAO#filtar(String)
     */
    public void filtrar() {
        // Obtém o texto de pesquisa do campo de texto e formata o padrão de nome com caracteres coringa
        String nome = "%" + txtPesquisaNome.getText() + "%";

        // Cria uma instância de FornecedoresDAO para acessar os dados dos clientes
        FornecedoresDAO dao = new FornecedoresDAO();

        // Obtém a lista de clientes filtrados do banco de dados
        List<Fornecedores> lista = dao.FiltarFornecedoresDao(nome);

        // Obtém o modelo da tabela e limpa todas as linhas existentes
        DefaultTableModel dados = (DefaultTableModel) tabela.getModel();
        dados.setNumRows(0);

        // Adiciona uma nova linha na tabela para cada cliente na lista
        for (Fornecedores c : lista) {
            dados.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getCnpj(),
                c.getEmail(),
                c.getTelefone(),
                c.getCelular(),
                c.getCep(),
                c.getEndereco(),
                c.getNumero(),
                c.getComplemento(),
                c.getBairro(),
                c.getCidade(),
                c.getEstado()
            });
        }
    }
//  Construtor
    public FormularioFornecedores(java.awt.Frame parent,boolean modal) {
        super(parent,modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        painel_guias = new javax.swing.JTabbedPane();
        painel_dados_pessoais = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnPesquisa = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JFormattedTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCep = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtComplento = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtCnpj = new javax.swing.JFormattedTextField();
        txtEndereco = new javax.swing.JTextField();
        cdEstados = new javax.swing.JComboBox<String>();
        painel_consulta = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtPesquisaNome = new javax.swing.JTextField();
        btnPesquisarNome = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de Fonecedores");
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cadastro de Fornecedores");

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("X");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel13MousePressed(evt);
            }
        });

        jProgressBar1.setPreferredSize(new java.awt.Dimension(945, 10));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addContainerGap())
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        painel_dados_pessoais.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Código:");

        txtCodigo.setEditable(false);
        txtCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
        });

        jLabel3.setText("Nome:");

        btnPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgFundo/IconButton/icons8-search-more-16.png"))); // NOI18N
        btnPesquisa.setText("Pesquisar");
        btnPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaActionPerformed(evt);
            }
        });

        jLabel4.setText("E-mail:");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel5.setText("Celular:");

        try {
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)# ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setText("Telefone:");

        jLabel7.setText("Endereço:");

        txtCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCidadeActionPerformed(evt);
            }
        });

        jLabel8.setText("Cep:");

        try {
            txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCepActionPerformed(evt);
            }
        });

        jLabel9.setText("Nº:");

        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        txtBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBairroActionPerformed(evt);
            }
        });

        jLabel10.setText("Bairro:");

        jLabel11.setText("Cidade:");

        jLabel12.setText("Complemento:");

        txtComplento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComplentoActionPerformed(evt);
            }
        });

        jLabel14.setText("CNPJ:");

        try {
            txtCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        txtEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnderecoActionPerformed(evt);
            }
        });

        cdEstados.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AC  ", "AL  ", "AP  ", "AM  ", "BA  ", "CE  ", "DF  ", "ES  ", "GO  ", "MA  ", "MT  ", "MS  ", "MG  ", "PA  ", "PB  ", "PR  ", "PE  ", "PI  ", "RJ  ", "RN  ", "RS  ", "RO  ", "RR  ", "SC  ", "SP  ", "SE  ", "TO", "```" }));

        javax.swing.GroupLayout painel_dados_pessoaisLayout = new javax.swing.GroupLayout(painel_dados_pessoais);
        painel_dados_pessoais.setLayout(painel_dados_pessoaisLayout);
        painel_dados_pessoaisLayout.setHorizontalGroup(
            painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(txtNome)
                        .addGap(12, 12, 12)
                        .addComponent(btnPesquisa)
                        .addGap(296, 296, 296))
                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                        .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                                .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cdEstados, 0, 101, Short.MAX_VALUE))
                            .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                                .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCep)
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel7)))
                                .addGap(12, 12, 12)
                                .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtEndereco)
                                    .addComponent(txtCelular))))
                        .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel_dados_pessoaisLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtNumero))
                            .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                                        .addGap(0, 114, Short.MAX_VALUE)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtComplento, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTelefone)))))))
                .addGap(26, 26, 26))
        );
        painel_dados_pessoaisLayout.setVerticalGroup(
            painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(btnPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNome))
                .addGap(12, 12, 12)
                .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))))
                .addGap(12, 12, 12)
                .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))))
                .addGap(21, 21, 21)
                .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtComplento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(cdEstados, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10)))
                .addGap(18, 18, 18)
                .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel14)))
                .addContainerGap())
        );

        painel_guias.addTab("Dados Pessoais", painel_dados_pessoais);

        painel_consulta.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel15.setText("Nome:");

        txtPesquisaNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisaNomeActionPerformed(evt);
            }
        });
        txtPesquisaNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaNomeKeyReleased(evt);
            }
        });

        btnPesquisarNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgFundo/IconButton/icons8-search-more-16.png"))); // NOI18N
        btnPesquisarNome.setText("Pesquisar");
        btnPesquisarNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarNomeActionPerformed(evt);
            }
        });

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nome", "CNPJ", "E-mail", "Telefone", "Celular", "CEP", "Endereço", "Número", "Complemento", "Bairro", "Cidade", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true, true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        javax.swing.GroupLayout painel_consultaLayout = new javax.swing.GroupLayout(painel_consulta);
        painel_consulta.setLayout(painel_consultaLayout);
        painel_consultaLayout.setHorizontalGroup(
            painel_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_consultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPesquisaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPesquisarNome)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
        );
        painel_consultaLayout.setVerticalGroup(
            painel_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_consultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painel_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPesquisaNome)
                    .addGroup(painel_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(btnPesquisarNome)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addContainerGap())
        );

        painel_guias.addTab("Consulta de Fornecedores", painel_consulta);

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgFundo/IconButton/icons8-add-user-male-38.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNovo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnNovo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgFundo/IconButton/icons8-usuário-verificado-masculino-38.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalvar.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgFundo/IconButton/icons8-editar-usuário-masculino-38.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditar.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgFundo/IconButton/icons8-remover-usuário-masculino-38.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExcluir.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgFundo/IconButton/icons8-impressão-49.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnImprimir.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(btnNovo)
                .addGap(52, 52, 52)
                .addComponent(btnSalvar)
                .addGap(60, 60, 60)
                .addComponent(btnEditar)
                .addGap(50, 50, 50)
                .addComponent(btnExcluir)
                .addGap(54, 54, 54)
                .addComponent(btnImprimir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(painel_guias)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painel_guias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnSalvar)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir)
                    .addComponent(btnImprimir))
                .addGap(21, 21, 21))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCidadeActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void txtCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCepActionPerformed

    private void txtBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBairroActionPerformed

    private void txtComplentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComplentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComplentoActionPerformed

    private void txtPesquisaNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaNomeActionPerformed

    private void txtEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnderecoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Salvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaActionPerformed
        Pesquisar();
    }//GEN-LAST:event_btnPesquisaActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        Ultilitarios util = new Ultilitarios();
        util.LimparTela(painel_dados_pessoais);//nome do painel 
    }//GEN-LAST:event_btnNovoActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        listar();
    }//GEN-LAST:event_formWindowActivated

    private void btnPesquisarNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarNomeActionPerformed

        filtrar();
    }//GEN-LAST:event_btnPesquisarNomeActionPerformed

    private void txtPesquisaNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaNomeKeyReleased
        filtrar();
    }//GEN-LAST:event_txtPesquisaNomeKeyReleased

    private void txtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Pesquisar();
        }
    }//GEN-LAST:event_txtNomeKeyPressed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked

        painel_guias.setSelectedIndex(0);
        txtCodigo.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
        txtNome.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
        txtCnpj.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
        txtEmail.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
        txtTelefone.setText(tabela.getValueAt(tabela.getSelectedRow(), 4).toString());
        txtCelular.setText(tabela.getValueAt(tabela.getSelectedRow(), 5).toString());
        txtCep.setText(tabela.getValueAt(tabela.getSelectedRow(), 6).toString());
        txtEndereco.setText(tabela.getValueAt(tabela.getSelectedRow(), 7).toString());
        txtNumero.setText(tabela.getValueAt(tabela.getSelectedRow(), 8).toString());
        txtComplento.setText(tabela.getValueAt(tabela.getSelectedRow(), 9).toString());
        txtBairro.setText(tabela.getValueAt(tabela.getSelectedRow(), 10).toString());
        txtCidade.setText(tabela.getValueAt(tabela.getSelectedRow(), 11).toString());
        cdEstados.setSelectedItem(tabela.getValueAt(tabela.getSelectedRow(), 12).toString());

    }//GEN-LAST:event_tabelaMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        Excluir();

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
    jProgressBar1.setIndeterminate(true); // Ativa o modo indeterminado 
    new Thread(() -> {
        try {    
            new relatorios.RelFornecedores();
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
              // Loga a interrupção da thread, caso aconteça
                e.printStackTrace();
            } finally {
              // Finaliza a barra de progresso
                SwingUtilities.invokeLater(() -> jProgressBar1.setIndeterminate(false));
                this.dispose(); // Fecha a janela atual
          }
        }).start();    
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void jLabel13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MousePressed
        this.dispose();
    }//GEN-LAST:event_jLabel13MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("windows".equals(info.getName())) {//Nimbus windows
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormularioFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new FormularioFornecedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnPesquisarNome;
    private javax.swing.JButton btnSalvar;
    public javax.swing.JComboBox<String> cdEstados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel painel_consulta;
    private javax.swing.JPanel painel_dados_pessoais;
    private javax.swing.JTabbedPane painel_guias;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JFormattedTextField txtCnpj;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtComplento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPesquisaNome;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
