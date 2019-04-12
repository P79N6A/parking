package com.zoeeasy.cloud.tool.vesta.service.impl.provider;

import com.zoeeasy.cloud.tool.vesta.util.IpUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

@Slf4j
public class DbMachineIdProvider implements MachineIdProvider {

    @Getter
    @Setter
    private long machineId;

    @Getter
    @Setter
    private JdbcTemplate jdbcTemplate;

    public DbMachineIdProvider() {
        log.debug("IpConfigurableMachineIdProvider constructed.");
    }

    public void init() {
        String ip = IpUtils.getHostIp();

        if (StringUtils.isEmpty(ip)) {
            String msg = "Fail to get host IP address. Stop to initialize the DbMachineIdProvider provider.";

            log.error(msg);
            throw new IllegalStateException(msg);
        }

        Long id = null;
        try {
            id = jdbcTemplate.queryForObject(
                    "select ID from DB_MACHINE_ID_PROVIDER where IP = ?",
                    new Object[]{ip}, Long.class);

        } catch (EmptyResultDataAccessException e) {
            // Ignore the exception
            log.error("No allocation before for ip {}.", ip);
        }

        if (id != null) {
            machineId = id;
            return;
        }
        log.info(
                "Fail to get ID from DB for host IP address {}. Next step try to allocate one.",
                ip);
        int count = jdbcTemplate
                .update("update DB_MACHINE_ID_PROVIDER set IP = ? where IP is null limit 1",
                        ip);

        if (count <= 0 || count > 1) {
            String msg = String
                    .format("Fail to allocte ID for host IP address {}. The {} records are updated. Stop to initialize the DbMachineIdProvider provider.",
                            ip, count);

            log.error(msg);
            throw new IllegalStateException(msg);
        }

        try {
            id = jdbcTemplate.queryForObject(
                    "select ID from DB_MACHINE_ID_PROVIDER where IP = ?",
                    new Object[]{ip}, Long.class);

        } catch (EmptyResultDataAccessException e) {
            // Ignore the exception
            log.error("Fail to do allocation for ip {}.", ip);
        }

        if (id == null) {
            String msg = String
                    .format("Fail to get ID from DB for host IP address {} after allocation. Stop to initialize the DbMachineIdProvider provider.",
                            ip);

            log.error(msg);
            throw new IllegalStateException(msg);
        }

        machineId = id;
    }

}
