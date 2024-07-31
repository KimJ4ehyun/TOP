package com.ssafy.top.hourfocustimes.application;

import com.ssafy.top.global.domain.CommonResponseDto;
import com.ssafy.top.global.exception.CustomException;
import com.ssafy.top.hourfocustimes.domain.HourFocusTimes;
import com.ssafy.top.hourfocustimes.domain.HourFocusTimesRepository;
import com.ssafy.top.hourfocustimes.dto.request.HourRequest;
import com.ssafy.top.onedays.domain.OneDays;
import com.ssafy.top.onedays.domain.OneDaysRepository;
import com.ssafy.top.users.domain.Users;
import com.ssafy.top.users.domain.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.ssafy.top.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class HourFocusTimesService {

    private final HourFocusTimesRepository hourFocusTimesRepository;

    private final OneDaysRepository oneDaysRepository;

    private final UsersRepository usersRepository;

    public CommonResponseDto<?> save(String loginId, HourRequest hourRequest){
        Long userId = usersRepository.findByLoginId(loginId)
                .map(Users::getId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        OneDays oneDays = oneDaysRepository.findByUserIdAndDateData(userId, LocalDate.now())
                .orElseThrow(() -> new CustomException(ONE_DAY_NOT_FOUND));

        int hour = hourRequest.getHour();

        if(hourFocusTimesRepository.findByOneDaysIdAndTimeUnit(oneDays.getId(), hour).isPresent()) {
            throw new CustomException(ALREADY_EXISTS);
        } else {
            HourFocusTimes hourFocusTimes = HourFocusTimes.builder()
                    .focusTime(0)
                    .timeUnit(hour)
                    .oneDays(oneDays)
                    .build();
            hourFocusTimesRepository.save(hourFocusTimes);
            return new CommonResponseDto<>("기준 시간이 추가되었습니다", 201);
        }
    }
}
