package com.gnsoftware.Ordem.Servico.services;

import com.gnsoftware.Ordem.Servico.dto.OSForm;
import com.gnsoftware.Ordem.Servico.model.OS;

public interface OSService {

    OS save(OSForm OSForm);

    OS update(Long id, OSForm OSForm);

    OS findById(Long id);

    OS delete(Long id);
}
