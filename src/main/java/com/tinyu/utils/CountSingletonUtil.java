package com.tinyu.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.util.StringUtils;

import java.io.*;

public class CountSingletonUtil {
    private static final Logger logger = LoggerFactory.getLogger(CountSingletonUtil.class);

    private Long accessNums;

    private static CountSingletonUtil single;

    /**
     * get instance
     *
     * @return
     */
    public static CountSingletonUtil getInstance() {
        if (single == null) {
            synchronized (CountSingletonUtil.class) {
                if (single == null) {
                    single = new CountSingletonUtil();
                }
            }
        }

        return single;
    }

    public Long getAccessNums(String txtFilePath) {
        try {
            if (accessNums == null) {
                File file = new File(txtFilePath);
                if (!file.exists()) {
                    file.createNewFile();
                }

                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(txtFilePath), "UTF-8"));
                String str = null;
                StringBuffer content = new StringBuffer();
                while ((str = in.readLine()) != null) {
                    content.append(str);
                }
                in.close();

                String numStr = content.toString();
                Long count = 0L;
                if (!StringUtils.isEmpty(numStr))
                    count = Long.valueOf(numStr);

                accessNums = count;
            }

            return accessNums;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return 0L;
        }
    }

    /**
     * count visitor. Can be replaced with Redis
     *
     * @param txtFilePath
     * @return
     */
    public synchronized Long GetVisitCount(String txtFilePath) {
        try {
            File file = new File(txtFilePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(txtFilePath), "UTF-8"));
            String str = null;
            StringBuffer content = new StringBuffer();
            while ((str = in.readLine()) != null) {
                content.append(str);
            }
            in.close();

            String numStr = content.toString();
            Long count = 0L;
            if (!StringUtils.isEmpty(numStr))
                count = Long.valueOf(numStr);

            count++;
            accessNums = count;

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFilePath), "UTF-8"));
            out.write(String.valueOf(count));

            out.flush();
            out.close();

            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }
}
