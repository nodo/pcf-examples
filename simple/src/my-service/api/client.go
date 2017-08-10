package api

import (
	"github.com/julienschmidt/httprouter"
	"io/ioutil"
	"my-service/db"
	"net/http"
)

type ClientAPI struct {
	store db.KVStore
}

func NewClientAPI(store db.KVStore) *ClientAPI {
	return &ClientAPI{
		store: store,
	}
}

func (client *ClientAPI) GetKeyHandler(response http.ResponseWriter, request *http.Request, params httprouter.Params) {
	//todo: auth check
	key := params.ByName("key")
	bucketName := params.ByName("bucket_name")
	if bucketName == "" || key == "" {
		response.WriteHeader(400)
		return
	}
	value, err := client.store.Get(bucketName, key)
	if err != nil {
		response.WriteHeader(500)
		return
	} else if value == nil {
		response.WriteHeader(404)
		return
	} else {
		response.Write(value)
	}
}

func (client *ClientAPI) PutKeyHandler(response http.ResponseWriter, request *http.Request, params httprouter.Params) {
	//todo: auth check
	key := params.ByName("key")
	bucketName := params.ByName("bucket_name")
	if bucketName == "" || key == "" {
		response.WriteHeader(400)
		return
	}
	data, err := ioutil.ReadAll(request.Body)
	if err != nil {
		response.WriteHeader(400)
		return
	} else {
		err := client.store.Put(bucketName, key, data)
		if err != nil {
			response.WriteHeader(500)
			return
		}
	}
}
