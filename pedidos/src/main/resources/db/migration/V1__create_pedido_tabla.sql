CREATE TABLE pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_controle VARCHAR(50) NOT NULL,
    data_cadastro DATE,
    nome VARCHAR(100) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    quantidade INT DEFAULT 1,
    codigo_cliente INT NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL
);