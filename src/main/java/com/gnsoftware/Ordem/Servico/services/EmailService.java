package com.gnsoftware.Ordem.Servico.services;

import com.gnsoftware.Ordem.Servico.dto.OSForm;
import com.gnsoftware.Ordem.Servico.model.Cliente;
import com.gnsoftware.Ordem.Servico.model.OS;

public interface EmailService {

    void enviarEmailOSAberta(Cliente cliente, OSForm osForm);
    void enviarEmailServicoFinalizado(OS os);
}
