package com.regitiny.catiny.tools.exception;

import java.net.URI;
import java.time.Instant;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

/**
 * author: @yuvytung
 * cảm hứng từ mèo Nhếch đã tạo ra ngoại lệ của nhếch (NhechException).
 * tất cả các trường hợp code bẩn chưa xử lý sẽ throw NhechException để nhếch ngồi chồm chỗm trong code
 * <p>
 * xin lỗi nhếch xuất hiện ở đâu cũng sẽ chửi
 */
@Log4j2
@Getter
public class NhechException extends AbstractThrowableProblem {

    private static final String nhechSay = "ối zời ơi . nhếch đang ngồi ở đây. đuổi nó đi chỗ khác đã (gọi yuvytung để bắt nhếch)";
    private final Instant time = Instant.now();

    public NhechException(URI type, String title, StatusType status, String detail) {
        super(type, title, status, detail);
        log.info(detail);
    }

    public NhechException() {
        super(null, null, Status.INTERNAL_SERVER_ERROR, nhechSay);
        log.error(nhechSay);
    }

    public NhechException(String youSay) {
        super(null, null, Status.BAD_REQUEST, youSay);
        log.warn(youSay);
    }
}
