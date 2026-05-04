package app.bean;

import app.dao.AuditTrailDao;
import app.model.AuditTrail;
import jakarta.ejb.Singleton;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;

@Singleton
public class AuditTrailBean {

    @Inject
    private AuditTrailDao auditTrailDao;

    public void save(@Observes AuditTrail auditTrail){
        auditTrail.setActivity(new Date() + ": "
            + auditTrail.getActivity());
        auditTrailDao.save(auditTrail);
    }

    public List<AuditTrail> list(AuditTrail filter){
        return auditTrailDao.findAll();
    }
}
