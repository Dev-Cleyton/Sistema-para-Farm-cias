/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.sistema.view;

import br.com.sistema.dao.ClientesDAO;
import br.com.sistema.dao.FornecedoresDAO;
import br.com.sistema.dao.ProdutosDAO;
import br.com.sistema.model.Clientes;
import br.com.sistema.model.Fornecedores;
import br.com.sistema.model.Produtos;
import br.com.sistema.ultilitarios.Ultilitarios;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.Arc2D;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cleyton
 */
public class FormularioProdutos extends javax.swing.JDialog {

    /**
     * Salva os dados de um cliente no banco de dados com base nas informações
     * fornecidas pela interface gráfica.
     *
     * Este método permite salvar um novo cliente, capturando as informações dos
     * campos da interface gráfica, verificando se os dados inseridos são
     * válidos, e armazenando-os no banco de dados. O método também inclui uma
     * validação para o campo de número, assegurando que seja inserido um valor
     * numérico válido. Após a operação de salvar, o painel de dados pessoais é
     * limpo para permitir novas inserções.
     *
     * O método segue os seguintes passos:
     *
     * 1. Cria um novo objeto {@link Clientes} e define seus atributos com os
     * valores capturados dos campos da interface gráfica. 2. Tenta converter o
     * valor do campo `txtNumero` para um número inteiro. Se houver uma exceção
     * de formato inválido (número mal formatado), exibe uma mensagem de erro e
     * interrompe a execução. 3. Chama o método `salvarDao` da classe
     * {@link ClientesDAO} para persistir os dados do cliente no banco de dados.
     * 4. Após o salvamento, utiliza a classe {@link Ultilitarios} para limpar
     * os campos da interface gráfica.
     *
     * Exemplo de uso:
     *
     * ``` Salvar(); ```
     *
     * @see ClientesDAO#salvarDao(Clientes)
     * @see Ultilitarios#LimparTela(javax.swing.JPanel)
     */
    public void Salvar() {
        // 1º Criar um novo objeto Clientes e definir seus atributos a partir dos campos da interface gráfica
        Produtos obj = new Produtos();        
        obj.setDescricao(txtDescricao.getText());
        obj.setPreco(Double.valueOf(txtPreco.getText()));
        obj.setQtd_estoque(Integer.valueOf(txtQtdEstoque.getText()));

        // Obtém o estado selecionado no combobo
        obj.setFornecedores((Fornecedores)cdFornecedor.getSelectedItem());

        // 2º Salvar no banco de dados
        ProdutosDAO dao = new ProdutosDAO();
        dao.SalvarProdutoDao(obj);

        // 3º Limpar o painel de dados pessoais após salvar o cliente
        Ultilitarios util = new Ultilitarios();
        util.LimparTela(painel_dados_pessoais);
    }

    /**
     * Edita os dados de um cliente existente no banco de dados.
     *
     * Este método coleta os dados preenchidos nos campos da interface gráfica e
     * os utiliza para atualizar as informações de um cliente no banco de dados.
     * Ele valida o campo "Número" para garantir que seja um valor numérico
     * antes de prosseguir com a edição.
     *
     * Passos do método:
     *
     * 1. Coleta os dados da interface gráfica (campos de texto) e atribui ao
     * objeto {@link Clientes}. 2. Valida o campo "Número", exibindo uma
     * mensagem de erro caso o valor inserido seja inválido. 3. Atualiza os
     * dados do cliente no banco de dados utilizando o método
     * {@link ClientesDAO#EditarDao(Clientes)}. 4. Após a edição, o formulário é
     * limpo para evitar duplicação de dados na interface.
     *
     * Exemplo de uso:
     *
     * ``` // O usuário preenche os campos da interface gráfica e pressiona o
     * botão de editar editar(); ```
     *
     * @see Clientes
     * @see ClientesDAO
     */
    public void Editar() {
        // 1º Criar um novo objeto Clientes e definir seus atributos a partir dos campos da interface gráfica
        Produtos obj = new Produtos();
        obj.setId(Integer.parseInt(txtCodigo.getText())); // ID do cliente a ser editado
        obj.setDescricao(txtDescricao.getText());
        obj.setPreco(Double.parseDouble(txtPreco.getText()));
        obj.setQtd_estoque(Integer.parseInt(txtQtdEstoque.getText()));
        
        // Obtém o estado selecionado no combobox        
        Fornecedores f = new Fornecedores();
        f =(Fornecedores) cdFornecedor.getSelectedItem();
        obj.setFornecedores(f);
        
        // 2º Atualizar os dados do cliente no banco de dados
        ProdutosDAO daop = new ProdutosDAO();
        daop.EditarProdutoDao(obj);

        // 3º Limpar o painel de dados pessoais após salvar o cliente
        Ultilitarios util = new Ultilitarios();
        util.LimparTela(painel_dados_pessoais);
    }

    /**
     * Exclui um cliente da interface gráfica e do banco de dados.
     *
     * Este método exclui um cliente com base no ID fornecido pelo campo
     * `txtCodigo` da interface gráfica. Após a exclusão, o painel de dados
     * pessoais é limpo.
     *
     * Passos do método:
     *
     * 1. Obtém o ID do cliente a ser excluído a partir do campo `txtCodigo`. 2.
     * Cria um objeto {@link Clientes} e define o ID. 3. Chama o método
     * `ExcluirDao` da classe {@link ClientesDAO} para realizar a exclusão no
     * banco de dados. 4. Após a exclusão, limpa todos os campos da interface
     * gráfica usando a classe {@link Ultilitarios}.
     *
     * Exemplo de uso:
     *
     * ``` // Pressionando um botão de excluir na interface gráfica Excluir();
     * ```
     *
     * @see Clientes
     * @see ClientesDAO
     * @see Ultilitarios
     */
    public void Excluir() {
        // 1º Obter o ID do cliente a partir do campo 'txtCodigo'
        Produtos obj = new Produtos();
        obj.setId(Integer.valueOf(txtCodigo.getText()));

        // 2º Criar uma instância de ClientesDAO e chamar o método ExcluirDao para remover o cliente
         ProdutosDAO daop = new ProdutosDAO();
        daop.ExcluirProdutoDao(obj);

        // 3º Limpar todos os campos do painel de dados pessoais após a exclusão
        Ultilitarios util = new Ultilitarios();
        util.LimparTela(painel_dados_pessoais);
    }

    /**
     * Realiza a pesquisa de um cliente com base no nome fornecido e exibe seus
     * dados na interface gráfica.
     *
     * Este método permite pesquisar um cliente pelo nome, preenchendo os campos
     * da interface gráfica com os dados do cliente encontrado. A busca é
     * realizada chamando o método {@link ClientesDAO#BuscarClienteDao(String)},
     * que acessa o banco de dados e retorna os dados do cliente correspondente.
     *
     * O método segue os seguintes passos: 1. Obtém o nome do cliente a ser
     * pesquisado a partir do campo de texto `txtNome`. 2. Cria uma instância de
     * {@link ClientesDAO} para acessar os dados do cliente. 3. Chama o método
     * `BuscarClienteDao` com o nome fornecido para obter o cliente do banco de
     * dados. 4. Se um cliente for encontrado (verificado se o nome não é
     * `null`), os campos da interface gráfica são preenchidos com os dados do
     * cliente, como código, nome, RG, CPF, email, telefone, celular, endereço,
     * número, complemento, bairro, cidade e estado. 5. Caso contrário, uma
     * mensagem de alerta é exibida, indicando que o cliente não foi encontrado.
     *
     * @see ClientesDAO#BuscarClienteDao(String)
     */
    public void Pesquisar() {
        // Obtém o nome do cliente a ser pesquisado a partir do campo de texto
        String nome = txtDescricao.getText();

        // Cria uma instância de Clientes e ClientesDAO para acessar os dados do cliente
        Produtos obj = new Produtos();       
        ProdutosDAO dao = new ProdutosDAO();
        
        Fornecedores f = new Fornecedores();
        FornecedoresDAO daof = new FornecedoresDAO();
        
        // Realiza a busca do cliente com base no nome fornecido
        obj = dao.BuscarProdutoDao(nome);

        // Verifica se o cliente foi encontrado
        if (obj.getDescricao() != null) {
            // Preenche os campos da interface gráfica com os dados do cliente
            txtCodigo.setText(String.valueOf(obj.getId()));
            txtDescricao.setText(obj.getDescricao());  // Corrigido para definir o nome corretamente
            txtPreco.setText(String.valueOf(obj.getPreco()));
            txtQtdEstoque.setText(String.valueOf(obj.getQtd_estoque()));
            
            f = daof.BuscarFornecedoresDao(obj.getFornecedores().getNome());
            cdFornecedor.getModel().setSelectedItem(f);
            
        } else {
            // Exibe uma mensagem se o cliente não for encontrado
            JOptionPane.showMessageDialog(null, "Produtos não encontrado!");
        }
    }

    /**
     * Creates new form FormularioClientes
     */
    /**
     * Lista todos os clientes na tabela exibida na interface gráfica.
     *
     * Este método recupera a lista de clientes do banco de dados usando a
     * classe {@link ClientesDAO} e atualiza a tabela na interface gráfica com
     * os dados dos clientes.
     *
     * O método segue os seguintes passos: 1. Cria uma instância de
     * {@link ClientesDAO} para acessar os dados dos clientes. 2. Obtém a lista
     * de clientes chamando o método {@link ClientesDAO#listar()}. 3. Obtém o
     * modelo da tabela associada ao componente {@link JTable} e limpa as linhas
     * existentes. 4. Adiciona uma nova linha para cada cliente na lista,
     * preenchendo a tabela com os dados do cliente.
     *
     * @see ClientesDAO#listar()
     */
    public void listar() {
        // Cria uma instância de ProdutosDAO para acessar os dados dos Produtos
        ProdutosDAO dao = new ProdutosDAO();

        // Obtém a lista de Produtos do banco de dados
        List<Produtos> lista = dao.ListarProdutoDao();

        // Obtém o modelo da tabela e limpa todas as linhas existentes
        DefaultTableModel dados = (DefaultTableModel) tabela.getModel();
        dados.setNumRows(0);

        // Adiciona uma nova linha na tabela para cada cliente na lista
        for (Produtos p : lista) {
            dados.addRow(new Object[]{
                p.getId(),
                p.getDescricao(),
                p.getPreco(),
                p.getQtd_estoque(),
                p.getFornecedores().getNome()
            });
        }
    }

    /**
     * Filtra e exibe clientes com base no nome fornecido na interface gráfica.
     *
     * Este método realiza uma busca de clientes na tabela `tb_clientes` com
     * base no nome fornecido pelo usuário. O nome é obtido a partir do campo de
     * texto `txtPesquisaNome`, e a busca é realizada utilizando caracteres
     * coringa (`%`) para permitir buscas parciais. Após obter os resultados, o
     * método atualiza a tabela na interface gráfica com os dados dos clientes
     * filtrados.
     *
     * O método segue os seguintes passos: 1. Obtém o texto de pesquisa do campo
     * de texto `txtPesquisaNome` e formata o padrão de nome com caracteres
     * coringa (`%`) para realizar a busca parcial. 2. Cria uma instância da
     * classe {@link ClientesDAO} para acessar os dados dos clientes. 3. Obtém a
     * lista de clientes filtrados chamando o método
     * {@link ClientesDAO#filtar(String)} com o padrão de nome formatado. 4.
     * Obtém o modelo da tabela associada ao componente {@link JTable} e limpa
     * todas as linhas existentes. 5. Adiciona uma nova linha na tabela para
     * cada cliente na lista, preenchendo a tabela com os dados do cliente.
     *
     * @see ClientesDAO#filtar(String)
     */
    public void filtrar() {
        // Obtém o texto de pesquisa do campo de texto e formata o padrão de nome com caracteres coringa
        String nome = "%" + txtPesquisaDescricao.getText() + "%";

        // Cria uma instância de ClientesDAO para acessar os dados dos clientes
        ProdutosDAO dao = new ProdutosDAO();

        // Obtém a lista de clientes filtrados do banco de dados
        List<Produtos> lista = dao.FiltarProdutoDao(nome);

        // Obtém o modelo da tabela e limpa todas as linhas existentes
        DefaultTableModel dados = (DefaultTableModel) tabela.getModel();
        dados.setNumRows(0);

        // Adiciona uma nova linha na tabela para cada cliente na lista
        for (Produtos p : lista) {
            dados.addRow(new Object[]{
                p.getId(),
                p.getDescricao(),
                p.getPreco(),
                p.getQtd_estoque(),
                p.getFornecedores().getNome()
            });
        }
    }

    public FormularioProdutos(java.awt.Frame parent,boolean modal) {
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
        jLabel6 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        painel_guias = new javax.swing.JTabbedPane();
        painel_dados_pessoais = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnPesquisa = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtQtdEstoque = new javax.swing.JTextField();
        cdFornecedor = new javax.swing.JComboBox();
        PanelImagem = new javax.swing.JPanel();
        textFeldDisplay = new javax.swing.JTextField();
        btnChooseImageFromComputer = new javax.swing.JButton();
        labelDisplayImage = new javax.swing.JLabel();
        painel_consulta = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtPesquisaDescricao = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de Produtos");
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
        jLabel1.setText("Cadastro de Produtos");

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("X");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });

        jProgressBar1.setName(""); // NOI18N
        jProgressBar1.setPreferredSize(new java.awt.Dimension(960, 10));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addComponent(jLabel6)
                .addContainerGap())
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        txtDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescricaoActionPerformed(evt);
            }
        });
        txtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescricaoKeyPressed(evt);
            }
        });

        jLabel3.setText("Descrição:");

        btnPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgFundo/IconButton/icons8-search-more-16.png"))); // NOI18N
        btnPesquisa.setText("Pesquisar");
        btnPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaActionPerformed(evt);
            }
        });

        jLabel4.setText("R$:");

        txtPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecoActionPerformed(evt);
            }
        });

        jLabel5.setText("QTD. Produto:");

        jLabel13.setText("Fornecedor:");

        txtQtdEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtdEstoqueActionPerformed(evt);
            }
        });

        cdFornecedor.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                cdFornecedorAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        cdFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cdFornecedorMouseClicked(evt);
            }
        });

        PanelImagem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Imagem", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        textFeldDisplay.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnChooseImageFromComputer.setText("Load Imagem");
        btnChooseImageFromComputer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImageFromComputerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelImagemLayout = new javax.swing.GroupLayout(PanelImagem);
        PanelImagem.setLayout(PanelImagemLayout);
        PanelImagemLayout.setHorizontalGroup(
            PanelImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnChooseImageFromComputer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelImagemLayout.createSequentialGroup()
                .addComponent(labelDisplayImage, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelImagemLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(textFeldDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelImagemLayout.setVerticalGroup(
            PanelImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelImagemLayout.createSequentialGroup()
                .addComponent(labelDisplayImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFeldDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChooseImageFromComputer))
        );

        javax.swing.GroupLayout painel_dados_pessoaisLayout = new javax.swing.GroupLayout(painel_dados_pessoais);
        painel_dados_pessoais.setLayout(painel_dados_pessoaisLayout);
        painel_dados_pessoaisLayout.setHorizontalGroup(
            painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                        .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel13)
                                .addGap(7, 7, 7)
                                .addComponent(cdFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painel_dados_pessoaisLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel_dados_pessoaisLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18))
                                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(50, 50, 50)))
                                .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                                        .addComponent(txtPreco)
                                        .addGap(50, 50, 50)
                                        .addComponent(jLabel5)
                                        .addGap(14, 14, 14)
                                        .addComponent(txtQtdEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(12, 12, 12)
                        .addComponent(btnPesquisa))
                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(PanelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
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
                .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisa))
                .addGap(7, 7, 7)
                .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4))
                    .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtQtdEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(painel_dados_pessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painel_dados_pessoaisLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13))
                    .addComponent(cdFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel_dados_pessoaisLayout.createSequentialGroup()
                .addComponent(PanelImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        painel_guias.addTab("Dados do Produto", painel_dados_pessoais);

        painel_consulta.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel15.setText("Descrição:");

        txtPesquisaDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisaDescricaoActionPerformed(evt);
            }
        });
        txtPesquisaDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaDescricaoKeyReleased(evt);
            }
        });

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Descrição", "Preço", "QTD. Estoque", "Fornecedor"
            }
        ));
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
                .addComponent(txtPesquisaDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPesquisar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
        );
        painel_consultaLayout.setVerticalGroup(
            painel_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_consultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painel_consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtPesquisaDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addContainerGap())
        );

        painel_guias.addTab("Consulta de Produtos", painel_consulta);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(painel_guias))
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
                        .addComponent(btnImprimir)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painel_guias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void txtDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoActionPerformed

    private void txtPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecoActionPerformed

    private void txtPesquisaDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaDescricaoActionPerformed

    private void txtQtdEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtdEstoqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtdEstoqueActionPerformed

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

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed

        filtrar();
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void txtPesquisaDescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaDescricaoKeyReleased
        filtrar();
    }//GEN-LAST:event_txtPesquisaDescricaoKeyReleased

    private void txtDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Pesquisar();
        }
    }//GEN-LAST:event_txtDescricaoKeyPressed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked

        painel_guias.setSelectedIndex(0);
        txtCodigo.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
        txtDescricao.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
        txtPreco.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
        txtQtdEstoque.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
        
        Fornecedores f = new Fornecedores();
        FornecedoresDAO daof = new FornecedoresDAO();
        
        f = daof.BuscarFornecedoresDao(tabela.getValueAt(tabela.getSelectedRow(), 4).toString());       
        cdFornecedor.removeAllItems();
        cdFornecedor.getModel().setSelectedItem(f);

    }//GEN-LAST:event_tabelaMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        Excluir();

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void cdFornecedorAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_cdFornecedorAncestorAdded
       
       
    }//GEN-LAST:event_cdFornecedorAncestorAdded

    private void cdFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdFornecedorMouseClicked
       FornecedoresDAO dao = new FornecedoresDAO();
       List<Fornecedores> lista = dao.ListarFornecedoreDao();
       cdFornecedor.removeAllItems();
       for(Fornecedores f : lista){
           cdFornecedor.addItem(f);
       }
    }//GEN-LAST:event_cdFornecedorMouseClicked

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
    jProgressBar1.setIndeterminate(true); // Ativa o modo indeterminado 
    new Thread(() -> {
        try {         
        new relatorios.RelProdutos();
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

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
       this.dispose();
    }//GEN-LAST:event_jLabel6MousePressed

    private void btnChooseImageFromComputerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImageFromComputerActionPerformed
        JFileChooser filechooser = new JFileChooser();
        //chooser only "png","jpg","jpeg"
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png","jpg","jpeg");
        filechooser.addChoosableFileFilter(fnef);
        int openDialog = filechooser.showOpenDialog(null);
        if(openDialog == filechooser.APPROVE_OPTION){
           File selectedFile = filechooser.getSelectedFile();
           String selectedImagePath = selectedFile.getAbsolutePath();
           textFeldDisplay.setText(selectedImagePath);
           ImageIcon ii = new ImageIcon(selectedImagePath);
           //Resizing image to fit JLabel
           Image image = ii.getImage().getScaledInstance(labelDisplayImage.getWidth(), labelDisplayImage.getHeight(),Image.SCALE_SMOOTH);
           labelDisplayImage.setIcon(new ImageIcon(image));           
        }
    }//GEN-LAST:event_btnChooseImageFromComputerActionPerformed

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
            java.util.logging.Logger.getLogger(FormularioProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new FormularioProdutos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelImagem;
    private javax.swing.JButton btnChooseImageFromComputer;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSalvar;
    public javax.swing.JComboBox cdFornecedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDisplayImage;
    private javax.swing.JPanel painel_consulta;
    private javax.swing.JPanel painel_dados_pessoais;
    public javax.swing.JTabbedPane painel_guias;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField textFeldDisplay;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtPesquisaDescricao;
    private javax.swing.JTextField txtPreco;
    private javax.swing.JTextField txtQtdEstoque;
    // End of variables declaration//GEN-END:variables
}
