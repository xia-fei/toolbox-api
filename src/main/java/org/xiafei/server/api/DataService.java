package org.xiafei.server.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataService implements DisposableBean, InitializingBean {
    private final String filePath = "/data/file";
    private final String fileName="toolbox.db";
    private final Logger LOGGER = LoggerFactory.getLogger(DataService.class);
    private Map<String, String> dataMap = new HashMap<>();

    public Map<String, String> get() {
        return dataMap;
    }

    @Override
    public void destroy() throws Exception {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        try (OutputStream outputStream = Files.newOutputStream(path.resolve(fileName))) {
            byte[] dataByte = SerializationUtils.serialize(dataMap);
            StreamUtils.copy(dataByte, outputStream);
            LOGGER.info("序列化文件保存成功");
        } catch (Exception e) {
            LOGGER.error("序列化文件保存失败", e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Path path = Paths.get(filePath).resolve(fileName);
        if (Files.exists(path)) {
            try (InputStream inputStream = Files.newInputStream(path)) {
                dataMap = (Map<String, String>) SerializationUtils.deserialize(StreamUtils.copyToByteArray(inputStream));
                LOGGER.info("序列化文件读取成功");
            } catch (Exception e) {
                LOGGER.error("序列化文件读取失败");
            }
        }
    }
}
