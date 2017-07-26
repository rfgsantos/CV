using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class RadarObject
{
    public Image icon { get; set; }
    public GameObject owner { get; set; }
}

public class Radar : MonoBehaviour {

    public Transform playerPos;
    float mapscale = 2.0f;
	public Camera mapCamera;
	private float distance = 400;

    public static List<RadarObject> radObjects = new List<RadarObject>();

    public static void RegisterRadarObject(GameObject o, Image i)
    {   
        //prefab - BLUE DOT OR YELLOW DOT OR AN IMAGE
		//image configurada por nós
        Image image = Instantiate(i);
        radObjects.Add(new RadarObject() { owner = o, icon = image});
    }

    public static void RemoveRadarObject(GameObject o)
    {
        List<RadarObject> newList = new List<RadarObject>();
        for (int i=0; i<radObjects.Count; i++)
        {
            if (radObjects[i].owner == o)
            {
                Destroy(radObjects[i].icon);
                continue;
            }
            else
            {
                newList.Add(radObjects[i]);
            }
        }

		radObjects.RemoveRange (0,radObjects.Count);
		radObjects.AddRange (newList);

    }


    void DrawRadarDots()
    {
        foreach (RadarObject ro in radObjects)
        {	
			Vector2 mop = new Vector2 (ro.owner.transform.position.x, ro.owner.transform.position.y);
			Vector2 pp = new Vector2 (playerPos.position.x, playerPos.position.y);

			if (Vector2.Distance (mop, pp) > distance) {
				ro.icon.enabled = false;
				continue;
			} else {
				ro.icon.enabled = true;
			}

			Vector3 screenPos = mapCamera.WorldToViewportPoint (ro.owner.transform.position);
			ro.icon.transform.SetParent (this.transform);


			RectTransform rt = this.GetComponent<RectTransform> ();

			Vector3[] corners = new Vector3[4];
			rt.GetWorldCorners (corners);
			screenPos.x = Mathf.Clamp(screenPos.x * rt.rect.width + corners[0].x, corners[0].x, corners[2].x);
			screenPos.y = Mathf.Clamp(screenPos.y * rt.rect.height + corners[0].y, corners[0].y, corners[1].y);

			screenPos.z = 0;
			ro.icon.transform.position = screenPos;
		}
    }
	
	// Update is called once per frame
	void Update () {
        DrawRadarDots();
    }

}
