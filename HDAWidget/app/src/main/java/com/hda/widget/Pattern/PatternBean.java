package com.hda.widget.Pattern;

import com.hda.widget.AppIcon.AppIconBean;
import java.util.ArrayList;
import java.util.List;

public class PatternBean {
    public int pattern_type;
    public int status;
    public String pattern_remain_time;
    public String pattern_image;
    public List<AppIconBean> app_icon_info=new ArrayList<AppIconBean>();
}
