package de.db.webapp.services.impl;

import de.db.webapp.repositories.SchweinRepository;
import de.db.webapp.services.PersonenServiceException;
import de.db.webapp.services.SchweineService;
import de.db.webapp.services.SchweineServiceException;
import de.db.webapp.services.mapper.SchweinMapper;
import de.db.webapp.services.models.Schwein;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SchweineServiceException.class, isolation = Isolation.READ_COMMITTED)
public class SchweineServiceImpl implements SchweineService {

    private final SchweinRepository repo;
    private final SchweinMapper mapper;

    @Override
    public boolean speichere(Schwein schwein) throws SchweineServiceException {
        try {
            boolean result = repo.existsById(schwein.getId());
            repo.save(mapper.convert(schwein));
            return result;
        } catch (Exception e) {
            throw new SchweineServiceException("upps", e);
        }
    }

    @Override
    public Optional<Schwein> findeZuId(String id) throws SchweineServiceException {
        try {
            return repo.findById(id).map(mapper::convert);
        } catch (Exception e) {
            throw new SchweineServiceException("upps", e);
        }
    }

    @Override
    public Iterable<Schwein> findeAlle() throws SchweineServiceException {
        try {
            return mapper.convert(repo.findAll());
        } catch (Exception e) {
            throw new SchweineServiceException("upps", e);
        }
    }

    @Override
    public boolean loesche(String id) throws SchweineServiceException {
        try {
            if( repo.existsById(id)) {
                repo.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new SchweineServiceException("upps", e);
        }
    }

    @Override
    public boolean fuettere(String id) throws SchweineServiceException {
        try {
            Optional<Schwein> optional = findeZuId(id);
            if(optional.isPresent()) {
                Schwein s = optional.get();
                s.fuettern();
                speichere(s);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new SchweineServiceException("upps", e);
        }
    }
}
