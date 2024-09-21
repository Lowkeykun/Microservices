package com.jerome.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

// Marks the class as a Spring Bean with the name auditAwareImpl. this allows spring to automatically discovers and manage it
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> { // interface AuditorAware is for spring security where auditor is represented as spring

    // this method returns the current auditor wrapped in optional
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Accounts_MS");
    }
}
