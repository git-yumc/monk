package com.baizhi.monk.controller;

import com.baizhi.monk.entity.Chapter;
import com.baizhi.monk.service.ChapterService;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yumc
 */
@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("select")
    public Map<String,Object> select(Integer page,Integer rows,String albumId){
        Map<String, Object> map = chapterService.selectAll(page, rows,albumId);
        return map;
    }
@RequestMapping("edit")
    public Map<String,Object> edit(Chapter chapter, String oper) {
        Map<String,Object> map=new HashMap<>(16);
        if(oper.equals("add")){
            chapter.setId(null);
            chapterService.insert(chapter);
             map.put("id",chapter.getId());
        }else if(oper.equals("edit")){
            chapterService.update(chapter);
            map.put("id",chapter.getId());
        }else if (oper.equals("del")){
            chapterService.delete(chapter);
        }
        return map;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile src, String id, HttpSession session, HttpServletRequest request) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        // 获取路径
        String realPath = session.getServletContext().getRealPath("/statics/audio");
        // 判断路径文件夹是否存在
        File file = new File(realPath);
        // 防止重名操作
        String originalFilename = src.getOriginalFilename();
        originalFilename = id+"_"+originalFilename;
        File file1 = new File(realPath, originalFilename);
        try {
            src.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MP3File read = (MP3File) AudioFileIO.read(file1);
        MP3AudioHeader mp3AudioHeader = read.getMP3AudioHeader();
        int trackLength = mp3AudioHeader.getTrackLength();
        String min = trackLength/60 +"分";
        String sed = trackLength%60 +"秒";
        String size=file1.length()/1024+"KB";
        Chapter chapter=new Chapter();
        chapter.setSize(size);
        chapter.setLength(min+sed);
        chapter.setId(id);
        chapter.setSrc(originalFilename);
        chapterService.update(chapter);
    }
}
