package darkchoco.simplebbs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing  // JPA Auditing 활성화. SimplebbsApplication에 있던 것을 Test 때문에 분리하여 여기에 설정.
public class JpaConfig {
}
