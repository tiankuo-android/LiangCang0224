package com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/11 0011.
 */

public class MagazineBean {
    String access_url;
    String addtime;
    String author_id;
    String author_name;
    String cat_id;
    String cat_name;
    String content;
    String cover_img;
    String cover_img_new;
    String hit_number;
    String nav_title;
    String taid;
    String topic_name;
    String topic_url;

    public MagazineBean() {
    }

    public MagazineBean(String access_url, String addtime, String author_id, String author_name, String cat_id, String cat_name, String content, String cover_img, String cover_img_new, String hit_number, String nav_title, String taid, String topic_name, String topic_url) {
        this.access_url = access_url;
        this.addtime = addtime;
        this.author_id = author_id;
        this.author_name = author_name;
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.content = content;
        this.cover_img = cover_img;
        this.cover_img_new = cover_img_new;
        this.hit_number = hit_number;
        this.nav_title = nav_title;
        this.taid = taid;
        this.topic_name = topic_name;
        this.topic_url = topic_url;
    }

    public String getAccess_url() {
        return access_url;
    }

    public void setAccess_url(String access_url) {
        this.access_url = access_url;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public String getCover_img_new() {
        return cover_img_new;
    }

    public void setCover_img_new(String cover_img_new) {
        this.cover_img_new = cover_img_new;
    }

    public String getHit_number() {
        return hit_number;
    }

    public void setHit_number(String hit_number) {
        this.hit_number = hit_number;
    }

    public String getNav_title() {
        return nav_title;
    }

    public void setNav_title(String nav_title) {
        this.nav_title = nav_title;
    }

    public String getTaid() {
        return taid;
    }

    public void setTaid(String taid) {
        this.taid = taid;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getTopic_url() {
        return topic_url;
    }

    public void setTopic_url(String topic_url) {
        this.topic_url = topic_url;
    }

    @Override
    public String toString() {
        return "MgzAuthorDetalisBean{" +
                "access_url='" + access_url + '\'' +
                ", addtime='" + addtime + '\'' +
                ", author_id='" + author_id + '\'' +
                ", author_name='" + author_name + '\'' +
                ", cat_id='" + cat_id + '\'' +
                ", cat_name='" + cat_name + '\'' +
                ", content='" + content + '\'' +
                ", cover_img='" + cover_img + '\'' +
                ", cover_img_new='" + cover_img_new + '\'' +
                ", hit_number='" + hit_number + '\'' +
                ", nav_title='" + nav_title + '\'' +
                ", taid='" + taid + '\'' +
                ", topic_name='" + topic_name + '\'' +
                ", topic_url='" + topic_url + '\'' +
                '}';
    }
}
