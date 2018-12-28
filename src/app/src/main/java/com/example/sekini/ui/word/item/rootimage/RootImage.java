package com.example.sekini.ui.word.item.rootimage;

import com.example.sekini.data.model.SekaniRootImagesEntity;
import com.example.sekini.data.model.embedded.SekaniRootDto;
import com.example.sekini.ui.word.item.IWordItem;
import com.example.sekini.utils.common.RunnableIn;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.LinkedList;
import java.util.List;

public class RootImage implements IWordItem {

    private SekaniRootDto sekaniRootDto;
    private RunnableIn<byte[]> runnableIn;

    public RootImage(SekaniRootDto sekaniRootDto,RunnableIn<byte[]> runnableIn) {
        this.sekaniRootDto = sekaniRootDto;
        this.runnableIn = runnableIn;
    }

    @Override
    public List<BaseRecyclerView> render() {

        List<BaseRecyclerView> lst = new LinkedList<>();
        if (sekaniRootDto.getImages.size() > 0) {
            for (SekaniRootImagesEntity entity : sekaniRootDto.getImages) {
                lst.add(new RootImageItem(entity.content, sekaniRootDto.englishWords,
                        sekaniRootDto.sekaniRootsEntity.rootWord,
                        sekaniRootDto.sekaniFormsEntity.title.toLowerCase(),runnableIn));
            }
        } else {
            lst.add(new RootImageItem(null, sekaniRootDto.englishWords,
                    sekaniRootDto.sekaniRootsEntity.rootWord,
                    sekaniRootDto.sekaniFormsEntity.title.toLowerCase(),runnableIn));
        }
        return lst;
    }
}
