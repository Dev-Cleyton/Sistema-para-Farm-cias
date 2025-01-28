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
import java.awt.event.KeyEvent;
import java.awt.geom.Arc2D;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cleyton
 */
public class FormularioEstoque extends javax.swing.JDialog {
    
    int idProduto, qtd_atualizada;//Variavel gogla 
    
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
        obj.setPreco(Double.valueOf(txtQTDAtual.getText()));
        obj.setQtd_estoque(Integer.valueOf(txtQtdNova.getText()));
        // 2º Salvar no banco de dados
        ProdutosDAO dao = new ProdutosDAO();
        dao.SalvarProdutoDao(obj);

        // 3º Limpar o painel de dados pessoais após salvar o cliente
        Ultilitarios util = new Ultilitarios();
        util.LimparTela(painel_Estoque);
    }

    public void Editar() {
        // 1º Criar um novo objeto Clientes e definir seus atributos a partir dos campos da interface gráfica
        Produtos obj = new Produtos();
        obj.setId(Integer.parseInt(txtCodigo.getText())); // ID do cliente a ser editado
        obj.setDescricao(txtDescricao.getText());
        obj.setPreco(Double.parseDouble(txtQTDAtual.getText()));
        obj.setQtd_estoque(Integer.parseInt(txtQtdNova.getText()));
        
        // Obtém o estado selecionado no combobox        
        Fornecedores f = new Fornecedores();
        obj.setFornecedores(f);
        
        // 2º Atualizar os dados do cliente no banco de dados
        ProdutosDAO daop = new ProdutosDAO();
        daop.EditarProdutoDao(obj);

        // 3º Limpar o painel de dados pessoais após salvar o cliente
        Ultilitarios util = new Ultilitarios();
        util.LimparTela(painel_Estoque);
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
        util.LimparTela(painel_Estoque);
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
            txtQTDAtual.setText(String.valueOf(obj.getPreco()));
            txtQtdNova.setText(String.valueOf(obj.getQtd_estoque()));
            
            f = daof.BuscarFornecedoresDao(obj.getFornecedores().getNome());
            
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
   /* public void filtrar() {
        // Obtém o texto de pesquisa do campo de texto e formata o padrão de nome com caracteres coringa
        String nome;// = "%" + txtPesquisaDescricao.getText() + "%";

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
    }*/

    public FormularioEstoque(java.awt.Frame parent,boolean modal) {
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
        painel_Estoque = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnPesquisa = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtQTDAtual = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtQtdNova = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de Estoque");
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
        jLabel1.setText("Estoque");

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("X");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });

        jProgressBar1.setPreferredSize(new java.awt.Dimension(962, 10));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap())
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        painel_guias.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consulta de Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        painel_Estoque.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        btnPesquisa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgFundo/IconButton/icons8-search-more-16.png"))); // NOI18N
        btnPesquisa.setText("Pesquisar");
        btnPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaActionPerformed(evt);
            }
        });

        jLabel4.setText("QTD. Atual:");

        txtQTDAtual.setEditable(false);
        txtQTDAtual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQTDAtualActionPerformed(evt);
            }
        });

        jLabel5.setText("QTD:");

        txtQtdNova.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtdNovaActionPerformed(evt);
            }
        });

        btnAdicionar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgFundo/IconButton/icons8-adicionar-propriedade-20.png"))); // NOI18N
        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painel_EstoqueLayout = new javax.swing.GroupLayout(painel_Estoque);
        painel_Estoque.setLayout(painel_EstoqueLayout);
        painel_EstoqueLayout.setHorizontalGroup(
            painel_EstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_EstoqueLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(painel_EstoqueLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnPesquisa))
            .addGroup(painel_EstoqueLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(10, 10, 10)
                .addComponent(txtQTDAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(16, 16, 16)
                .addComponent(txtQtdNova, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnAdicionar))
        );
        painel_EstoqueLayout.setVerticalGroup(
            painel_EstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_EstoqueLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(painel_EstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painel_EstoqueLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(painel_EstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painel_EstoqueLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(btnPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(painel_EstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painel_EstoqueLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(painel_EstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(painel_EstoqueLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel5))
                            .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQtdNova, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel_EstoqueLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQTDAtual)
                        .addContainerGap())))
        );

        painel_guias.addTab("Dados do Produto", painel_Estoque);

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgFundo/IconButton/icons8-adicionar-produto-38.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNovo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnNovo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgFundo/IconButton/icons8-caixa-entregue-38.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalvar.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgFundo/IconButton/icons8-fluxo-de-inventário-38.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditar.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgFundo/IconButton/icons8-esgotado-38.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExcluir.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgFundo/IconButton/icons8-embalagem-38.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnImprimir.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tabela.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
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
        jScrollPane2.setViewportView(tabela);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painel_guias)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(btnNovo)
                        .addGap(52, 52, 52)
                        .addComponent(btnSalvar)
                        .addGap(60, 60, 60)
                        .addComponent(btnEditar)
                        .addGap(50, 50, 50)
                        .addComponent(btnExcluir)
                        .addGap(54, 54, 54)
                        .addComponent(btnImprimir)
                        .addGap(0, 85, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painel_guias, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void txtQTDAtualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQTDAtualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQTDAtualActionPerformed

    private void txtQtdNovaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtdNovaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtdNovaActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Salvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaActionPerformed
        Pesquisar();
    }//GEN-LAST:event_btnPesquisaActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        Ultilitarios util = new Ultilitarios();
        util.LimparTela(painel_Estoque);//nome do painel 
    }//GEN-LAST:event_btnNovoActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        listar();
    }//GEN-LAST:event_formWindowActivated

    private void txtDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Pesquisar();
        }
    }//GEN-LAST:event_txtDescricaoKeyPressed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        Excluir();

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
      idProduto = Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(),0).toString());
      txtCodigo.setText(tabela.getValueAt(tabela.getSelectedRow(),0).toString());
      txtDescricao.setText(tabela.getValueAt(tabela.getSelectedRow(),1).toString());
      txtQTDAtual.setText(tabela.getValueAt(tabela.getSelectedRow(),3).toString());     
    }//GEN-LAST:event_tabelaMouseClicked

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        try {
            int qtdAtual,qtd_nova;
            qtdAtual = Integer.valueOf(txtQTDAtual.getText());
            qtd_nova = Integer.valueOf(txtQtdNova.getText());
            qtd_atualizada = qtdAtual+qtd_nova;
            ProdutosDAO daop = new ProdutosDAO();
            daop.adicionarEstoque(idProduto, qtd_atualizada); 
            new Ultilitarios().LimparTela(painel_Estoque);//Limpa tela
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro" + e);            
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed

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
            java.util.logging.Logger.getLogger(FormularioEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new FormularioEstoque().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel painel_Estoque;
    private javax.swing.JTabbedPane painel_guias;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtQTDAtual;
    private javax.swing.JTextField txtQtdNova;
    // End of variables declaration//GEN-END:variables
}
