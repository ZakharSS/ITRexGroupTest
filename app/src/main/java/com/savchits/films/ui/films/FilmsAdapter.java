package com.savchits.films.ui.films;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.savchits.films.R;
import com.savchits.films.model.FilmDescription;
import com.savchits.films.model.FilmGroup;
import com.squareup.picasso.Picasso;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

import static android.view.animation.Animation.RELATIVE_TO_SELF;


/**
 * Created by Zakhar Savchits on 20.04.2018.
 */
public class FilmsAdapter extends ExpandableRecyclerViewAdapter<FilmsAdapter.FilmGroupViewHolder, FilmsAdapter.FilmChildViewHolder> {

    public FilmsAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public FilmGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film, parent, false);
        return new FilmGroupViewHolder(v);
    }

    @Override
    public FilmChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film_description, parent, false);
        return new FilmChildViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(FilmChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final FilmDescription description = ((FilmGroup) group).getItems().get(childIndex);
        holder.setChild(description.getDescription());
    }

    @Override
    public void onBindGroupViewHolder(FilmGroupViewHolder holder, int flatPosition, ExpandableGroup group) {
        final FilmGroup filmGroup = (FilmGroup) group;
        holder.setGroup(filmGroup.getImage(), filmGroup.getName(), filmGroup.getName_eng());
    }

    public class FilmGroupViewHolder extends GroupViewHolder {

        public ImageView filmImage;
        public TextView filmTitleRu;
        public TextView filmTitleEn;
        public ImageView arrow;

        public FilmGroupViewHolder(View itemView) {
            super(itemView);
            filmImage = itemView.findViewById(R.id.ivFilmIcon);
            filmTitleRu = itemView.findViewById(R.id.tvFilmTitleRu);
            filmTitleEn = itemView.findViewById(R.id.tvFilmTitleEng);
            arrow = itemView.findViewById(R.id.ivChevronDown);
        }

        public void setGroup(String image, String titleRu, String titleEn) {
            Picasso.get().load(image).into(filmImage);
            filmTitleRu.setText(titleRu);
            filmTitleEn.setText(titleEn);
        }

        @Override
        public void expand() {
            animateExpand();
        }

        @Override
        public void collapse() {
            animateCollapse();
        }

        private void animateExpand() {
            RotateAnimation rotate =
                    new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setFillAfter(true);
            arrow.startAnimation(rotate);
        }

        private void animateCollapse() {
            RotateAnimation rotate =
                    new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setFillAfter(true);
            arrow.startAnimation(rotate);
        }
    }

    public class FilmChildViewHolder extends ChildViewHolder {

        public TextView filmDescription;

        public FilmChildViewHolder(View itemView) {
            super(itemView);
            filmDescription = itemView.findViewById(R.id.tvFilmDescription);
        }

        public void setChild(String description) {
            filmDescription.setText(description);
        }
    }
}
