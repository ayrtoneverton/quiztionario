package com.quiztionario.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.quiztionario.model.Category;
import com.quiztionario.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteAdapter extends BaseAdapter implements Filterable, View.OnClickListener {
	private List<Category> list = new ArrayList<>();
	private LayoutInflater inflater;
	private OnSelectAutoComplete selectItem;

	public AutoCompleteAdapter(Context context, OnSelectAutoComplete selectItem){
		this.inflater = LayoutInflater.from(context);
		this.selectItem = selectItem;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int i) {
		return list.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	@SuppressLint("ViewHolder")
	public View getView(int i, View view, ViewGroup viewGroup) {
		View v = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
		((TextView) v.findViewById(android.R.id.text1)).setText(list.get(i).getName());
		v.setOnClickListener(this);
		v.setTag(i);
		return v;
	}

	@Override
	public Filter getFilter() {
		return new Filter() {
			@Override
			protected FilterResults performFiltering(CharSequence search) {
				FilterResults filterResults = new FilterResults();
				if (search != null && search.length() > 1) {
					List<Category> result = CategoryService.getInstance().find(search.toString());
					filterResults.values = result;
					filterResults.count = result.size();
				}
				return filterResults;
			}

			@Override
			protected void publishResults(CharSequence search, FilterResults results) {
				if (results != null && results.count > 0) {
					list = (List<Category>) results.values;
					notifyDataSetChanged();
					selectItem.onSelectAutoComplete(null);
				} else {
					notifyDataSetInvalidated();
				}
			}
		};
	}

	@Override
	public void onClick(View view) {
		notifyDataSetInvalidated();
		selectItem.onSelectAutoComplete(list.get((Integer) view.getTag()));
	}

	public interface OnSelectAutoComplete{
		void onSelectAutoComplete(Category item);
	}
}
