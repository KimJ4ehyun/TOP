package com.ssafy.top.appfocustimes.dao;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AppFocusTimeSumDao {

    private Long userId;

    private Long focusTimeSum;
}
